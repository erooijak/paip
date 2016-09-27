(ns paip.chapter5
  (:require [clojure.test :refer :all]
            [paip.exercise5_1 :refer :all]))

(defn tmp-simple-equal
  "Are x and y equal? (Don't check inside strings.)
  Note: seems to do the same as =. Not yet sure about wanted semantics."
  [x y]
  (if (or (string? x) (string? y))
    (= x y)
    (and (simple-equal (first x) (first y))
         (simple-equal (rest x) (rest y)))))

(deftest test-exercise-5-1
  (testing "simple-equal should simply test for equality"
    (is (= (simple-equal "a" "b")))))
