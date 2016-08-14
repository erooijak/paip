(ns paip.exercise3_5
  (:gen-class))

; Exercise 3.5 [h] *(Exercise in altering structure.) Write a program that will play the
; role of guesser in the game Twenty Questions. The user of the program will have in mind
; any type of thing. The program will ask questions of the user, which must be answered yes
; or no, or "it" when the program has guessed it. If the program runs out of guesses, it
; gives up and asks the user what "it" was. At first the program will not play well, but
; each time it plays, it will remember the user's replies and use them for subsequent
; guesses.


; The idea seems to be that the program has a tree of questions and remembers the answer
; for each node.

; To solve it we need state. We need to build a tree of questions and answers.

; I use the following structure:

(def questions-and-answers
  "Questions and answers database atom.
  Keywords are used to traverse the tree.
  An answer needs to be non-existent (nil) or in the form of a string."
  (atom
   {:is_it_a_programming_language?
    {:yes
     {:is_it_statically_typed?
      {:yes
       nil
       :no
       nil}}
     :no
     {:ca_va?
      {:yes
       nil
       :no
       nil}}}}))

; To improve this program it should be possible to add new questions and answers. This way
; the program can become bigger and bigger if we have non-trolling users.

; For now, we just ask the questions and update if the user has an answer.

(defn get-remaining
  "Gets the remaining questions and answers from the questions-and-answers database.
  previous should be a vector of the previous questions and answers."
  [previous]
  (if (empty? previous) @questions-and-answers
      (get-in @questions-and-answers previous)))

(defn get-next-question
  "Gets the next question from questions-and-answers
  previous should be a vector of the previous questions and answers."
  [previous]
  (-> previous get-remaining ffirst))

(defn further-questions?
  "True if there is an answer for a question. False if not."
  [previous]
  (if (empty? previous) true
      (not (nil? (get-remaining previous)))))

(defn found-answer?
  "True if we have found the answer."
  [previous]
  (string? (get-remaining previous)))

(defn set-answer!
  "Sets the answer to the value provided.
  previous should be a vector of the previous questions and answers."
  [previous answer]
  (swap! questions-and-answers assoc-in previous answer))

(defn key->string
  "Create a string of the answer of question key."
  [key]
  (-> (name key) (clojure.string/replace "_" " ") clojure.string/capitalize))

(defn string->key
  "Create a key of the answer string."
  [answer]
  (-> answer (clojure.string/replace " " "_") clojure.string/lower-case keyword))

(defn driver-loop
  "Asks the questions, updates the answers.
  previous is vector containing the previous questions."
  ([] (driver-loop [])) ; Start with no previous provided answers.
  ([previous]
   (let [question (get-next-question previous)
         ask-it (println (key->string question)) ; ask-it is dummy variable to not create nested lets
         answer (read)
         print-answer (println answer) ; dummy, idem
         previous (conj previous question (string->key answer))]
     (cond (found-answer? previous)
           (let [found-answer (get-remaining previous)]
             (println (str "The answer is: " found-answer)))
           (not (further-questions? previous))
           (do (println "What was it?")
               (let [given-answer (str (read))]
                 (println given-answer)
                 (set-answer! previous given-answer)
                 (println "Thank you.")))
           :else (recur previous)))))

; (driver-loop)
