(ns paip.simple-test
  (:require [clojure.test :refer :all]
            [paip.simple :refer :all]))

;; TODO: generative syntax seems to be an excellent choice for using Clojure spec
;;       for property-based testing.

;;; Test helpers

(defn- one-two-or-three?
  "Returns true if n is one two or three, false if not"
  [n]
  (or (= 1 n) (= 2 n) (= 3 n)))

(defn- one-two-or-three-list?
  "Returns true if n is a list containing one two or three, false if not"
  [n]
  (or (= '(1) n) (= '(2) n) (= '(3) n)))

;;; Common Lisp to Clojure conversion

(deftest test-cl-elt-is-nth
  (testing "nth is similar to elt (http://clhs.lisp.se/Body/f_elt.htm)"
    (is (= (nth "0123456789" 6) \6))))

(deftest test-cl-rand-nth-is-random
  (testing "rand-nth is similar to random (http://clhs.lisp.se/Body/f_random.htm)"
    (is (instance? Long (rand-nth '(1 2 3))))))

(deftest test-cl-append-is-concat
  (testing "concat is similar to append (http://jtra.cz/stuff/lisp/sclr/append.html)"
    (is concat nil)
    (is (concat '(1 2 3) '(1 2 3)))
    (is (concat '(1 2 3) '(4 5 6)) '(1 2 3 4 5 6))
    (is (concat '(1) '(2) '(3)) '(1 2 3))))

(deftest test-assoc-is-key-on-map
  (testing "Key should return the value on map as assoc on list
            (http://clhs.lisp.se/Body/f_assocc.htm)"
    (is (= (:sentence simple-grammar)
           [[:noun-phrase :verb-phrase]]))))

(deftest test-listp-is-list
  (testing "list? is similar to listp (http://clhs.lisp.se/Body/f_listp.htm)"
    (is (= (list? nil) false) "Should be false in Clojure's case")
    (is (= (list? '()) true))
    (is (= (list? '(1 2 3)) true))))

;;; Test actual code

(deftest test-one-of
  (testing "one-of should pick one element of set, and make a list of it"
    (is (one-two-or-three-list? (one-of '(1 2 3))))))

(deftest test-rewrites
  (testing "rewrites should return a list (!) of possible rewrites"
    (is (= (rewrites :sentence) [[:noun-phrase :verb-phrase]]))))

(deftest test-generate-sentence
  (testing "generate sentence should generate a sentence of at least length 3"
    (is (>= (count (generate :sentence)) 3))))

(deftest test-combine-all
  (testing "combine all should create all combinations"
    (is (= (combine-all '((a) (b)) '((1) (2)))
           '((a 1) (b 1) (a 2) (b 2))))
    (is (= (combine-all '(("ab") ("bc")) '(("de") ("ef")))
           '(("ab" "de") ("bc" "de") ("ab" "ef") ("bc" "ef"))))))

(deftest test-generate-all
  (testing "256 sentences because every sentence has the form Article-Noun-Verb-Article-Noun
            and there are two articles, four nouns and four verbs (2x4x4x2x4 = 256)"
    (is (= (count (generate-all :sentence)) 256))))
