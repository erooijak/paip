(ns paip.simple
  (:gen-class))

; Clojure translation of the Common Lisp code in Chapter 2.
; Original: http://norvig.com/paip/simple.lisp

;;; Code translated from Paradigms of Artificial Intelligence Programming
;;; Copyright (c) 1991 Peter Norvig

(defn random
  "Returns a random between 0 (inclusive) and upper-bound (exclusive)"
  [upper-bound]
  (-> upper-bound rand int))

(defn one-of
  "Pick one element of set, and make a list of it."
  [set]
  (list (rand-nth set)))

(defn flatten-once
  "Flattens a collection one level"
  [coll]
  (apply concat coll))

(def simple-grammar
  "A grammar for a trivial subset of English"
  {:sentence    [[:noun-phrase :verb-phrase]]
   :noun-phrase [[:article :noun]]
   :verb-phrase [[:verb :noun-phrase]]
   :article     #{"the" "a"}
   :noun        #{"man" "ball" "woman" "table"}
   :verb        #{"hit" "took" "saw" "liked"}})

(def grammar
  "The grammar used by generate.  Initially, this is
  simple-grammar, but we can switch to other grammars."
  simple-grammar)

;;; ==============================

(defn rule-lhs
  "The left hand side of a rule."
  [rule]
  (->> rule keys (apply concat)))

(defn rule-rhs
  "The right hand side of a rule."
  [rule]
  (->> rule vals (apply concat)))

(defn rewrites
  "Return a list of the possible rewrites for this category."
  [category]
  (rule-rhs (select-keys grammar [category])))

;;; ==============================

(defn generate
  "Generate a random sentence or phrase"
  [phrase]
  (cond (vector? phrase)
        (mapcat generate phrase)
        (not (empty? (rewrites phrase)))
        (generate (rand-nth (rewrites phrase)))
        :else
        (list phrase)))

;;; ==============================

;(defparameter *bigger-grammar*
;  '((sentence -> (noun-phrase verb-phrase))
;    (noun-phrase -> (Article Adj* Noun PP*) (Name) (Pronoun))
;    (verb-phrase -> (Verb noun-phrase PP*))
;    (PP* -> () (PP PP*))
;    (Adj* -> () (Adj Adj*))
;    (PP -> (Prep noun-phrase))
;    (Prep -> to in by with on)
;    (Adj -> big little blue green adiabatic)
;    (Article -> the a)
;    (Name -> Pat Kim Lee Terry Robin)
;    (Noun -> man ball woman table)
;    (Verb -> hit took saw liked)
;    (Pronoun -> he she it these those that)))
;
;;; (setf *grammar* *bigger-grammar*)
;
;;;; ==============================
;
;(defun generate-tree (phrase)
;  "Generate a random sentence or phrase,
;  with a complete parse tree."
;  (cond ((listp phrase)
;         (mapcar #'generate-tree phrase))
;        ((rewrites phrase)
;         (cons phrase
;               (generate-tree (random-elt (rewrites phrase)))))
;        (t (list phrase))))
;
;;;; ==============================
;
;(defun generate-all (phrase)
;  "Generate a list of all possible expansions of this phrase."
;  (cond ((null phrase) (list nil))
;        ((listp phrase)
;         (combine-all (generate-all (first phrase))
;                      (generate-all (rest phrase))))
;        ((rewrites phrase)
;         (mappend #'generate-all (rewrites phrase)))
;        (t (list (list phrase)))))
;
;(defun combine-all (xlist ylist)
;  "Return a list of lists formed by appending a y to an x.
;  E.g., (combine-all '((a) (b)) '((1) (2)))
;  -> ((A 1) (B 1) (A 2) (B 2))."
;  (mappend #'(lambda (y)
;               (mapcar #'(lambda (x) (append x y)) xlist))
;           ylist))



