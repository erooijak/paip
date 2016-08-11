(ns paip.chapter2.tests
  (:require [clojure.test :refer :all]
            [paip.exercise2_1 :refer :all]
            [paip.exercise2_2 :refer :all]
            [paip.exercise2_4 :refer :all]))

(deftest test-exercise-2-1
  (testing "generate should work with keyword?"
    (is (= (count (generate-without-calling-rewrites-twice :sentence)) 5) "length of simple-grammar sentence is 5")))

(deftest test-exercise-2-2
  (testing "generate should work with non-terminal?"
    (is (= (count (generate-non-terminal :sentence)) 5) "length of simple-grammar sentence is 5")))

(deftest test-exercise-2-4
  (testing "cross-product all should create all combinations"
    (is (= (cross-product + '(1 2 3) '(10 20 30))
           '(11 21 31
             12 22 32
             13 23 33)))
    (is (= (cross-product list '(a b) '(1 2))
           '((a 1) (a 2) (b 1) (b 2))))))
