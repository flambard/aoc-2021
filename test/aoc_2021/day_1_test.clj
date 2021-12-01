(ns aoc-2021.day-1-test
  (:require [clojure.test :refer :all]
            [aoc-2021.day-1 :refer :all]))

(deftest test-1
  (let [measurements [
                      199
                      200
                      208
                      210
                      200
                      207
                      240
                      269
                      260
                      263
                      ]]
    (testing "Counting measurements"
      (is (= 7 (count-measurement-increases measurements 1))))))

(deftest test-2
  (let [measurements [
                      199
                      200
                      208
                      210
                      200
                      207
                      240
                      269
                      260
                      263
                      ]]
    (testing "Counting measurements with sliding window size 3"
      (is (= 5 (count-measurement-increases measurements 3))))))

