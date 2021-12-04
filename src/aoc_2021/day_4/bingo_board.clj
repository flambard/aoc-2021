(ns aoc-2021.day-4)

(defn make-bingo-board [grid]
  (let [rows (mapv vec (partition 5 grid))
        empty-rows (vec (repeat 5 (vec (repeat 5 nil))))]
    {:unmarked {:rows rows
                :columns (apply mapv vector rows)}
     :marked {:rows empty-rows
              :columns empty-rows}}))

(defn mark-number [unmarked marked number]
  (let [index (.indexOf unmarked number)]
    (if (= index -1)
      [unmarked marked]
      [(assoc unmarked index nil) (assoc marked index number)])))

(defn bingo-row? [marked]
  (every? integer? marked))

(defn bingo? [{{rows :rows
                columns :columns} :marked}]
  (or (some bingo-row? rows)
      (some bingo-row? columns)))

(defn mark-number-on-board-at [{unmarked :unmarked marked :marked} number index]
  (let [[u-rows m-rows]
        (mark-number (nth (:rows unmarked) index) (nth (:rows marked) index) number)
        [u-cols m-cols]
        (mark-number (nth (:columns unmarked) index) (nth (:columns marked) index) number)]
    {:unmarked
     {:rows (assoc (:rows unmarked) index u-rows)
      :columns (assoc (:columns unmarked) index u-cols)}
     :marked
     {:rows (assoc (:rows marked) index m-rows)
      :columns (assoc (:columns marked) index m-cols)}}))

(defn mark-number-on-board [board number]
  (reduce #(mark-number-on-board-at %1 number %2)
          board
          (range 5)))

(defn board-score [{{rows :rows} :unmarked}]
  (reduce #(reduce + %1 (filter (complement nil?) %2)) 0 rows))

(defn play-bingo-to-win [boards numbers]
  (loop [[number & remaining-numbers] numbers
         boards-in-play (mapv make-bingo-board boards)]
    (when-not (nil? remaining-numbers)
      (let [marked-boards (mapv #(mark-number-on-board % number) boards-in-play)
            boards-with-bingo (filter bingo? marked-boards)]
        (if (not-empty boards-with-bingo)
          (* number (board-score (first boards-with-bingo)))
          (recur remaining-numbers marked-boards))))))


(defn play-bingo-to-lose [boards numbers]
  (loop [[number & remaining-numbers] numbers
         boards-in-play (mapv make-bingo-board boards)]
    (when-not (nil? remaining-numbers)
      (let [marked-boards (mapv #(mark-number-on-board % number) boards-in-play)
            {boards-with-bingo true remaining-boards nil} (group-by bingo? marked-boards)]
        (if (empty? remaining-boards)
          (* number (board-score (first boards-with-bingo)))
          (recur remaining-numbers remaining-boards))))))
