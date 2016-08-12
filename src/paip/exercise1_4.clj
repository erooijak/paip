(ns paip.exercise1_4
  (:gen-class))

;; Exercise 1.4 [m] Write a function that counts the number of times an expression
;; occurs anywhere within another expression

(defn count-anywhere
  "Counts the number of times an expression occurs anywhere within another
  expression."
  [expression-to-search expression]
  (->> expression
       flatten
       (filter #{expression-to-search})
       count))
