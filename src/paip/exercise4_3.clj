(ns paip.exercise4_3
  (:require [paip.gps1 :refer :all])
  (:import [paip.gps1 Op]))

;; Exercise 4.3 [h]
;; GPS does not recognize the situation where a goal is accidentally solved as
;; part of achieving another goal. Consider the goal of eating dessert. Assume
;; that there are two operators available: eating ice cream (which requires
;; having the ice cream) and eating cake (which requires having the cake).
;; Assume that we can buy a cake, and that the bakery has a deal where it
;; gives out free ice cream to each customer who purchases and eats a cake.


;; (1) Design a list of operators to represent this situation.

(def dessert-ops
  [(Op. :eating-ice-cream
        [:having-ice-cream]
        [:eating-dessert]
        [:having-ice-cream])
   (Op. :eating-cake
        [:having-cake]
        [:eating-dessert :having-ice-cream]
        [:having-cake])
   (Op. :buying-cake
        [:able-to-buy-cake]
        [:having-cake]
        nil)])


;; (2) give GPS the goal of eating dessert. Show that with the right list of
;; operators, GPS will device to eat ice cream, then decide to buy and eat the
;; cake in order to get the free ice cream, and then go ahead and eat the ice
;; cream, even though the goal of eating dessert has already been achieved by
;; eating the cake.

(GPS [:able-to-buy-cake]
     [:eating-dessert]
     dessert-ops)

;; => Executing :buying-cake
;;    Executing :eating-cake
;;    Executing :eating-ice-cream


;; (3) Fix GPS so that it does not manifest this problem.

;; Add a check to see is operations are already achieved:

(defn already-achieved?
  "Checks if new operations are not already achieved"
  [ops]
  (some (fn [op] (some #(= op %) @state)) ops))

;; And add this check to apply-op (and ensure to return true since the value is used)

(defn apply-op
  "Print a message and update *state* if op is applicable."
  [op]
  (when (every? achieve (:preconds op))
    (when-not (already-achieved? (:add-list op))
      (reset! state (clojure.set/difference (set @state) (:del-list op)))
      (reset! state (clojure.set/union (set @state) (:add-list op)))
      (println "Executing" (:action op)))
    true))

;; Leads to:

;; => Executing :buying-cake
;;    Executing :eating-cake
