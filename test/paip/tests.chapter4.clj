(ns paip.chapter4
  (:require [clojure.test :refer :all]
            [paip.exercise4_1 :refer :all]))

(deftest test-exercise-4-1
  (testing "add-all-positions should add element e at every position in coll"
    (is (= (add-all-positions 1 [2 3 4])
           [[1 2 3 4]
            [2 1 3 4]
            [2 3 1 4]
            [2 3 4 1]]))
    (is (= (into [] (mapcat (partial add-all-positions 1) [[2 3 4] [5 6 7]]))
           [[1 2 3 4] [2 1 3 4] [2 3 1 4] [2 3 4 1] [1 5 6 7] [5 1 6 7] [5 6 1 7] [5 6 7 1]]))))
  ;; (testing "permutations should generate all permutations of the input"
  ;;   (is (= (permutations [:a :b :c])
  ;;          [[:a :b :c]
  ;;           [:a :c :b]
  ;;           [:b :a :c]
  ;;           [:b :c :a]
  ;;           [:c :a :b]
  ;;           [:c :b :a]]))))


