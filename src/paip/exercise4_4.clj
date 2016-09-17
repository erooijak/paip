(ns paip.exercise4_4
  (:require [paip.gps1 :refer :all])
  (:import [paip.gps1 Op]))

;; Exercise 4.4 [h]
;; The Not Looking after You Don't Leap Problem. Write a program that keeps
;; track of the remaining goals so that it does not get stuck considering only
;; one possible operation when others will eventually lead to the goal. Hint:
;; have achieve take an extra argument indicating the goals that remain to
;; be achieved after the current goal is achieved. achieve should succeed
;; only if it can achieve the current goal and also achieve-all the
;; remaining goals.

;; Exercise for the reader.
;; Working Common Lisp code: http://norvig.com/paip/gps.lisp.
