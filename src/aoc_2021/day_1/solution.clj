(ns aoc-2021.day-1)

(defn count-measurement-increases
  [measurements window-size]
  (loop [increases 0
         previous (reduce + (take window-size measurements))
         remaining measurements]
    (let [window (take window-size remaining)
          next (reduce + window)]
      (if (< (count window) window-size)
        increases
        (recur
         (if (< previous next) (inc increases) increases)
         next
         (rest remaining))))))
