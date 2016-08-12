(ns paip.exercise3_3
  (:gen-class))

; Exercise 3.3 [m] The function `cons` can be seen as a special case of one
; of the other functions listed previously. Which one?

(list* 1 '(2 3 4)) ; => (cons 1 '(2 3 4))

; Note: princ prints suitable output without escape characters and binds some
; values to special Common Lisp parameters. I use println.

; Clojure has no dotted pair notation since it avoids the linked list data
; structure and uses the abstraction of the sequence. See
; [sequences](http://clojure. org/reference/sequences): "A seq is a logical list,
; and unlike most Lisps where the list is represented by a concrete, 2-slot
; structure, Clojure uses the ISeq interface to allow many data structures to
; provide access to their elements as sequences."

; For a list (special type of sequence) we can introduce it.

;;; ==============================

(defn single-valued?
  "Checks if value is single-valued.
  Source: stackoverflow.com/questions/11782534/am-i-using-atom-wrong-or-there-is-
  something-else#11783268."
  [x]
  (not (or (nil? x)
           (.. x getClass isArray)
           (some #(instance? % x) [clojure.lang.Counted
                                   clojure.lang.IPersistentCollection
                                   java.util.Collection
                                   java.util.Map]))))

;;; ==============================

(declare create-dotted-pair)
(defn create-dotted-pair-rest
  "Creates dotted pair string representation for the rest of a list."
  [lst]
  (str " . " (create-dotted-pair lst)))

(defn create-dotted-pair
  "Creates dotted-pair string representation of list."
  [lst]
  (cond (single-valued? lst) (str lst)
        (empty? lst) (str "'()")
        (seq? lst) (str "("
                        (create-dotted-pair (first lst))
                        (create-dotted-pair-rest (rest lst))
                        ")")
        :else (str lst)))

(defn print-dotted-pair
  "Prints dotted-pair string representation of list."
  [lst]
  (print (create-dotted-pair lst)))
