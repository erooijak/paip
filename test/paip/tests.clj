(ns paip.tests
  (:require [clojure.test :refer :all]
            [paip.exercise1_1 :refer :all]
            [paip.exercise1_2 :refer :all]
            [paip.exercise1_3 :refer :all]
            [paip.exercise1_4 :refer :all]))

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
    (is (count-atoms '(1 2 3)) 3)
    (is (count-atoms '(1 (2) 3)) 3)
    (is (count-atoms '((1 (2) 3) :a)) 4)
    (is (count-atoms '(1 nil 3)) 3)))

(deftest test-exercise-1-4
  (testing "count-anywhere"
    (is (count-anywhere ':a '(:a :b :c)) 1)
    (is (count-anywhere ':a '(:a ((:a) :b) :c)) 2)))

(deftest test-exercise-1-5
  (testing "dot-product"
    (is (dot-product '(10 20) '(3 4)) 110)))

