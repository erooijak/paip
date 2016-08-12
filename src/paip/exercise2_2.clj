(ns paip.exercise2_3
  (:require paip.simple)
  (:gen-class))

; Exercise 2.3 [m] Write a trivial grammar for some other language. This can
; be a natural language other than English, or perhaps a subset of a
; computer language.

(def simple-clj-grammar
  "A grammar for a trivial subset of Clojure."
  {:code                [[:definition-phrase :function-definition]]
   :definition-phrase   [[:definition :names :docstring]]
   :function-definition [[:library-definitions :parameter-types]]
   :definition          #{"defn"}
   :docstring           #{"wtf?" "TODO"}
   :names               #{"my-method" "awesome?" "change-it!"}
   :library-definitions #{"map" "mapcat" "inc" "dec"}
   :parameter-types     #{"string" "int" "long" "bool"}})

(repeatedly 10 #(generate :code))
; =>
; (("defn" "my-method" "TODO" "dec" "bool")
;  ("defn" "my-method" "TODO" "mapcat" "int")
;  ("defn" "awesome?" "wtf?" "dec" "bool")
;  ("defn" "my-method" "wtf?" "mapcat" "string")
;  ("defn" "change-it!" "wtf?" "dec" "long")
;  ("defn" "awesome?" "TODO" "inc" "int")
;  ("defn" "my-method" "TODO" "inc" "string")
;  ("defn" "change-it!" "wtf?" "mapcat" "string")
;  ("defn" "awesome?" "TODO" "map" "string")
;  ("defn" "my-method" "wtf?" "inc" "string"))
