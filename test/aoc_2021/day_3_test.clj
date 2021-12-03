(ns aoc-2021.day-3-test
  (:require [clojure.test :refer :all]
            [aoc-2021.day-3 :refer :all]))

(def example-1
  [
   "00100"
   "11110"
   "10110"
   "10111"
   "10101"
   "01111"
   "00111"
   "11100"
   "10000"
   "11001"
   "00010"
   "01010"
   ])

(deftest test-1
  (testing "Gamma rate"
    (is (= 22 (gamma-rate example-1)))))

(deftest test-2
  (testing "Epsilon rate"
    (is (= 9 (epsilon-rate example-1)))))

(deftest test-3
  (testing "Oxygen generator rating"
    (is (= 23 (oxygen-generator-rating example-1)))))

(deftest test-4
  (testing "CO2 scrubber rating"
    (is (= 10 (co2-scrubber-rating example-1)))))

(deftest test-5
  (testing "Life support rating"
    (is (= 230 (life-support-rating example-1)))))
