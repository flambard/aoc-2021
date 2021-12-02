(ns aoc-2021.day-2)

(defn calculate-position [commands]
  (reduce (fn [position [direction amount]]
            (case direction
              :forward (-> position
                           (update :horizontal + amount)
                           (update :depth + (* amount (get position :aim))))
              :up (update position :aim - amount)
              :down (update position :aim + amount)))
          {:aim 0
           :depth 0
           :horizontal 0}
          commands))
