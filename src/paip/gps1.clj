(ns paip.gps1)

;;; -*- Mode: Lisp; Syntax: Common-Lisp; -*-
;;; Code from Paradigms of Artificial Intelligence Programming
;;; Copyright (c) 1991 Peter Norvig
;;; Code translated to Clojure by me.

;;;; File gps1.lisp: First version of GPS (General Problem Solver)

(def state
  "The current state: a list of conditions."
  (atom nil))

(def ops
  "A list of available operators."
  (atom nil))

(defrecord Op [action preconds add-list del-list])

(defn apply-op
  "Print a message and update *state* if op is applicable."
  [op]
  (when (every? achieve (:preconds op))
    (println (list :executing (:action op)))
    (reset! state (clojure.set/difference state (:del-list op)))
    (reset! state (clojure.set/union state (:add-list op)))
    t))

(defn appropriate?
  "An op is appropriate to a goal if it is in its add list."
  [goal op]
  (some #(= % goal) (:add-list op)))

(defn achieve
  "A goal is achieved if it already holds,
  or if there is an appropriate op for it that is applicable."
  [goal]
  (or (some #(= % goal) @state)
      (some apply-op
            (filter #(appropriate? goal %) @ops))))

(defn GPS
  "General Problem Solver: achieve all goals using *ops*."
  [state' goals ops']
  (reset! state state')
  (reset! ops ops')
  (if (every? achieve goals) :solved))

;;; ==============================

(def school-ops
  [(Op. :drive-son-to-school
        [:son-at-home :car-works]
        [:son-at-school]
        [:son-at-home])
   (Op. :shop-installs-battery
        [:car-needs-battery :shop-knows-problem :shop-has-money]
        [:car-works]
        nil)
   (Op. :tell-shop-problem
        [:in-communication-with-shop]
        [:shop-knows-problem]
        nil)
   (Op. :telephone-shop
        [:know-phone-number]
        [:in-communication-with-shop]
        nil)
   (Op. :look-up-number
        [:have-phone-book]
        [:know-phone-number]
        nil)
   (Op. :give-shop-money
        [:have-money]
        [:shop-has-money]
        [:have-money])])

(GPS [:son-at-home :car-needs-battery :have-money :have-phone-book]
     [:son-at-school]
     school-ops)
