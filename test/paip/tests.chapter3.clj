(ns paip.chapter3.tests
  (:require [clojure.test :refer :all]
            [paip.exercise3_2 :refer :all]
            [paip.exercise3_3 :refer :all]
            [paip.exercise3_4 :refer :all]
            [paip.exercise3_5 :refer :all]
            [paip.exercise3_8 :refer :all]
            [paip.exercise3_9 :refer :all]
            [paip.exercise3_12 :refer :all]))

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
             {:yes nil, :no nil}}
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


(deftest test-exercise-3-8
  (testing "llast should find second before last"
    (is (= (llast '(:t 1))
           :t)))
  (testing "get-value should return the value for a specified key from right to left"
    (is (= (get-value :not-available '(:test-fn +))
           nil))
    (is (= (get-value :test-fn '(:test-fn +))
           '+))
    (is (= (get-value :test-fn '(:test-fn - :test-fn +))
           '+)))
  (testing "find-all uses rightmost keyword/value pair when more than one is specified"
    (is (= (find-all 1 '(1 1 2 3 4 5 6) :test-fn > :key-fn inc :test-fn =)
           '(2 2)))
    (is (= (find-all 1 '(1 1 2 3 4 5 6) :test-fn = :key-fn inc :test-fn >)
           '(3 4 5 6 7)))))

(deftest test-exercise-3-9
  (testing "length using reduce"
    (is (= (length '(1 2 3 4 5))
           5))
    (is (= (length '(1 '(2 3) 4 5))
           3))))

(deftest test-exercise-3-12
  (testing "as-sentence"
    (is (= (as-sentence ["this" "is" "awesome"])
           "This is awesome."))))
