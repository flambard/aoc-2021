(ns aoc-2021.day-3)

(defn bit-count-to-bitstring [bit-count]
  (apply str (map #(if (< % 0) 0 1) bit-count)))

(defn count-bits-per-position [report f]
  (let [initial (repeat (count (first report)) 0)
        bit-count (reduce (fn [acc number]
                            (map #(+ %1 (f %2)) acc number))
                          initial
                          report)]
    (bit-count-to-bitstring bit-count)))

(defn gamma-rate [report]
  (-> report
      (count-bits-per-position #(case % \0 -1 \1 +1))
      (Integer/parseInt 2)))

(defn epsilon-rate [report]
  (-> report
      (count-bits-per-position #(case % \0 +1 \1 -1))
      (Integer/parseInt 2)))

(defn power-consumption [report]
  (let [gamma (gamma-rate report)
        epsilon (epsilon-rate report)]
    (* gamma epsilon)))





(defn find-rating [report bit-criteria]
  (loop [index 0
         values report]
    (let [bits-count (count-bits-per-position values #(case % \0 -1 \1 +1))
          filtered-values (filter #(bit-criteria (nth % index) (nth bits-count index)) values)]
      (if (empty? (rest filtered-values))
        (Integer/parseInt (first filtered-values) 2)
        (recur (inc index) filtered-values)))))

(defn oxygen-generator-rating [report]
  (let [bits-count (count-bits-per-position report #(case % \0 -1 \1 +1))]
    (find-rating report (fn [bit bit-count]
                          (= bit bit-count)))))

(defn co2-scrubber-rating [report]
  (let [bits-count (count-bits-per-position report #(case % \0 -1 \1 +1))]
    (find-rating report (fn [bit bit-count]
                          (not (= bit bit-count))))))

(defn life-support-rating [report]
  (* (oxygen-generator-rating report)
     (co2-scrubber-rating report)))

