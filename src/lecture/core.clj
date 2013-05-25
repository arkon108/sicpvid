(ns lecture.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn hello []
  (println "Hell, O'World!"))


;; MIT lecture on Lisp
;; for HP people in 1986

;; examples translated to Clojure
;; while I'm learning Clojure flavor of Lisp
;;
;; Lection 1B: Procedures and Processes; Substitution Model
;;
;; two addition functions
;; very similar in execution, very different shape when expanded

;; get the sum of 2 numbers using peano arithmetic,
;; taking items from one pile and putting them on another
(defn sumpeano [x y]
  (if (= x 0)
    y
    (sumpeano (dec x) (inc y))))

;; get the sum of 2 numbers by taking all the items from one pile
;; and then return them one by one
;; this function has different shape
(defn sumrec [x y]
  (if (= x 0)
    y
    (inc (sumrec (dec x) y)))
  )




;; fibonnacci sequence
;; obviously not optimized or efficient with all
;; obvious recursion calls
;;
;; illustrative, expanding this shows a tree, and
;; the cost of it is big, the longest branch
;;

(defn fib [x]
  (if (>= 2 x)
    x
    (+
      (fib (- x 1))
      (fib (- x 2))
      )))


;;
;; Lecture 2A
;; Higher Order Functions
;;
;; this lecture should smash the idea this is like programming
;; in Basic or Pascal, just with a funny syntax

;; sum-int
;; function for recursive summing of two integers
;; between a and b
(defn sum-int [a b]
  (if (> a b)
    0
    (+ a (sum-int (inc a) b))))
