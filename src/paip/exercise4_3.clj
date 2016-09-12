(ns paip.exercise4_3)

;; GPS does not recognize the situation where a goal is accidentally solved as
;; part of achieving another goal. Consider the goal of eating dessert. Assume
;; that there are two operators available: eating ice cream (which requires
;; having the ice cream) and eating cake (which requires having the cake).
;; Assume that we can buy a cake, and that the bakery has a deal where it
;; gives out free ice cream to each customer who purchases and eats a cake.
;; (1) Design a list of operators to represent this situation. (2) give GPS the
;; goal of eating dessert. Show that with the right list of operators, GPS
;; will device to eat ice cream, then decide to buy and eat the cake in order
;; to get the free ice cream, and then go ahead and eat the ice cream, even
;; though the goal of eating dessert has already been achieved by eating the
;; cake. (3) Fix GPS so that it does not manifest this problem.
