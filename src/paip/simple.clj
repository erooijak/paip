(ns paip.simple
  (:gen-class))

; Clojure translation of the Common Lisp code in Chapter 2.
; Original: http://norvig.com/paip/simple.lisp

;;; Code translated from Paradigms of Artificial Intelligence Programming
;;; Copyright (c) 1991 Peter Norvig

(defn one-of
  "Pick one element of set, and make a list of it."
  [set]
  (list (rand-nth set)))

(def simple-grammar
  "A grammar for a trivial subset of English"
  {:sentence    [[:noun-phrase :verb-phrase]]
   :noun-phrase [[:article :noun]]
   :verb-phrase [[:verb :noun-phrase]]
   :article     #{"the" "a"}
   :noun        #{"man" "ball" "woman" "table"}
   :verb        #{"hit" "took" "saw" "liked"}})

(def bigger-grammar
  "A somewhat bigger grammar for a trivial subset of English"
  {:sentence    [[:noun-phrase :verb-phrase]]
   :noun-phrase [[:article :adj* :noun :pp*] [:name] [:pronoun]]
   :verb-phrase [[:verb :noun-phrase :pp*]]
   :pp*         [[] [:pp :pp*]]
   :adj*        [[] [:adj :adj*]]
   :pp          [[:prep :noun-phrase]]
   :prep        #{"to" "in" "by" "with" "on"}
   :adj         #{"big" "little" "blue" "green" "adiabatic"}
   :article     #{"the" "a"}
   :name        #{"Pat" "Kim" "Lee" "Terry" "Robin"}
   :noun        #{"man" "ball" "woman" "table"}
   :verb        #{"hit" "took" "saw" "liked"}
   :pronoun     #{"he" "she" "it" "these" "those" "that"}})

;;; ==============================

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

(generate :sentence)
; => (One example)
;    ("Kim" "saw" "Pat")

;;; ==============================

(defn generate-tree
  "Generate a random sentence or phrase,
  with a complete parse tree."
  [phrase]
  (cond (vector? phrase)
        (map generate-tree phrase)
        (not (empty? (rewrites phrase)))
        (cons phrase
              (generate-tree (rand-nth (rewrites phrase))))
        :else (list phrase)))

(generate-tree :sentence)
; => (one example)
;    (:sentence (:noun-phrase (:Article "the")
;                             (:Adj*)
;                             (:Noun "ball")
;                             (:PP*))
;               (:verb-phrase (:Verb "liked")
;                             (:noun-phrase
;                              (:Name "Robin"))
;                             (:PP*)))

;;;; ==============================

(defn combine-all
  "Return a list of lists formed by appending a y to an x.
  E.g., (combine-all '((a) (b)) '((1) (2)))
  -> ((A 1) (B 1) (A 2) (B 2))."
  [xlist ylist]
  (mapcat (fn [y]
            (map (fn [x] (concat x y)) xlist))
          ylist))

;; TODO: There is a bug in generate-all. For sentences that are constructed out of noun-phrase
;;       or verb-phrases the resulting sentences are displayed in characters.
(defn generate-all
  "Generate a list of all possible expansions of this phrase.
   Only works for non-recursive grammars."
  [phrase]
  (cond (vector? phrase)
        (combine-all (generate-all (first phrase))
                     (mapcat generate-all (rest phrase)))
        (not (empty? (rewrites phrase)))
        (mapcat generate-all (rewrites phrase))
        :else
        (list phrase)))

(generate-all :noun)
; => ("table" "man" "woman" "ball")

(generate-all :noun-phrase)
; => ((\a \t \a \b \l \e) (\t \h \e \t \a \b \l \e) (\a \m \a \n) (\t \h \e \m \a \n)
;    (\a \w \o \m \a \n) (\t \h \e \w \o \m \a \n) (\a \b \a \l \l) (\t \h \e \b \a \l \l))
