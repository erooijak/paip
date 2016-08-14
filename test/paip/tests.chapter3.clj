(ns paip.chapter3.tests
  (:require [clojure.test :refer :all]
            [paip.exercise3_2 :refer :all]
            [paip.exercise3_3 :refer :all]
            [paip.exercise3_4 :refer :all]
            [paip.exercise3_5 :refer :all]))

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

(deftest test-exercise-3-5
  (testing "get-next-question should return map of remaining when previous question is provided."
    (is (= (get-remaining [:is_it_a_programming_language?])
           {:yes
             {:is_it_statically_typed?
              {:yes nil, :no nil}},
             :no
             {:ca_va?
              {:yes nil, :no nil}}}))
    (is (= (get-remaining [:is_it_a_programming_language?
                           :yes
                           :is_it_statically_typed?])
           {:yes nil, :no nil}))
    (is (= (get-remaining [:is_it_a_programming_language?
                           :yes
                           :is_it_statically_typed?
                           :yes])
           nil))
    (is (= (get-next-question []))
        :is_it_a_programming_language?)
    (is (= (get-next-question [:is_it_a_programming_language?
                               :yes]))
        :is_it_statically_typed?)
    (is (further-questions? []) true)
    (is (= (further-questions? [:is_it_a_programming_language?]) true))
    (is (not (further-questions? [:is_it_a_programming_language?
                                  :yes
                                  :is_it_statically_typed?
                                  :yes]))))
  (testing "key-string should transform keys into human readable format."
    (is (= (string->key "Yes") :yes))
    (is (= (key->string :yes) "Yes"))
    (is (= (string->key "Is it a programming language?") :is_it_a_programming_language?))
    (is (= (key->string :is_it_a_programming_language?) "Is it a programming language?"))))

