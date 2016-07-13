(ns paip.tests
  (:require [clojure.test :refer :all]
            [paip.exercise1_1 :refer :all]
            [paip.exercise1_2 :refer :all]
            [paip.exercise1_3 :refer :all]))

(deftest test-exercise-1-1
  (testing "last-name"
    (is (= (last-name '("Rex" "Morgan" "Md"))     "Morgan"))
    (is (= (last-name '("Morton" "Downey" "Jr.")) "Downey"))))

(deftest test-exercise-1-2
  (testing "power"
    (is (= (power 3 2)  9))
    (is (= (power 3 3)  27))
    (is (= (power 4 2)  16))
    (is (= (power -4 2) 16))))

(deftest test-exercise-1-3
  (testing "count-atoms"
    (is (count-atoms '(a (b) c)) 3)))
