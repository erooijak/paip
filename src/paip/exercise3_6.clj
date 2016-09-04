(ns paip.exercise3_6)

; Exercise 3.6 [s] *Given the following initialization for the lexical
; variable a and the special variable *b*, what will be the value of the 1st
; form?*

; (translated to Clojure)

(def a 'global-a)
(def ^:dynamic *b* 'global-b)
(def f (fn [] *b*))

(let [a   'local-a
      *b* 'local-b]
  (list a *b* (f) (var-get #'a) (var-get #'*b*)))

; => (local-a local-b global-b global-a global-b)

; I hoped to see the dynamic scoping at work in (var-get) #'*b* so that the showed version was returned, butlast; this did not work.

