(ns paip.chapter3.tests
  (:require [clojure.test :refer :all]
            [paip.exercise3_2 :refer :all]
            [paip.exercise3_3 :refer :all]
            [paip.exercise3_4 :refer :all]))

(deftest test-exercise-3-2
  (testing "cons special case of list*"
    (is (= (cons 1 '(2 3 4))
           (list* 1 '(2 3 4))))))

(deftest test-exercise-3-3
  (testing "create-dotted-pair should create the dotted-pairs."
    (is (= (create-dotted-pair (cons 1 (cons 2 (cons 3 (cons 4 '())))))
           "(1 . (2 . (3 . (4 . '()))))"))
    (is (= (create-dotted-pair '(1 2 3 4))
           "(1 . (2 . (3 . (4 . '()))))"))
    (is (= (create-dotted-pair '(1 2 (3 4)))
           "(1 . (2 . ((3 . (4 . '())) . '())))"))))

(deftest test-exercise-3-4
  (testing "Impossible to create improper list in Clojure."
    (is (thrown? IllegalArgumentException (cons 2 3)))))

