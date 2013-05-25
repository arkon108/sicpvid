;; MIT lecture on Lisp
;; for HP people in 1986
;;
;; since I'm learning Clojure, I'm watching the videos
;; and translating the examples

(ns lecture.core)


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

;; when expanded, this function shows a linear growth

;; get the sum of 2 numbers by taking all the items from one pile
;; and then return them one by one
;; this function has different shape
(defn sumrec [x y]
  (if (= x 0)
    y
    (inc (sumrec (dec x) y)))
  )

;; when expanded, this shows that it depends on the size of
;; the first argument


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


;; sum-sq
(defn sum-sq [a b]
  (if (> a b)
    0
    (+ (* a a) (sum-sq (inc a) b))))


;; there is a simple idea behind this
;; we have two sums here, summing two sequences,
;; (summing up some integers first, and then summing up some squares)
;; but the values of the things we're summing up are not relevant for the
;; sum function


;; Leibniz's formula for finding pi over 8

(defn pi-sum [a b]
  (if (> a b)
    0
    (+ (/ 1 (* a (+ a 2)))
       (pi-sum (+ a 4) b))))


;; here is the general procedure
;;
;; (defn <name> [a b]
;;   (if (> a b)
;;     0
;;     (+ (<term> a)
;;        (<name> (<next> a) b))))

;; define sigma
;; ie. the sum Higher Order Function (HOF)
(defn sum+ [TERM A NEXT B]
  (if (> A B)
    0
    (+ (TERM A)
       (sum+ TERM (NEXT A) NEXT B))
  ))

;; let's rewrite our functions now, using the sum+ HOF

;; idk how to pass a square function, so I defined a helper
(defn square [x] (* x x))

;; sum up all integers
;; identity just returns the param
(defn sum-int+ [a b]
  (sum+ identity a inc b))


;; sum up all squares
(defn sum-sq+ [a b]
  (sum+ square a inc b))


;; pi sum
(defn pi-sum+ [a b]
  (sum+ (fn [i] (/ 1 (* i (+ i 2))))
        a
        (fn [x] (+ x 4))
        b
        ))

;; the ^ above functions
;; serve to illustrate the HOF idea,
;; and the lambda/inline anonymous functions
;; being passed as parameters


;; following is Heron of Alexandria's
;; method of finding square roots; sqrt-h
;;
;; first is the code copy from the slide:
;;
;; (define (sqrt x)
;;   (define trolerance 0.00001)
;;   (define (good-enuf? y)
;;     (< (abs (- (* y y) x)) tolerance))
;;   (define (improve y)
;;     (average (/ x y) y))
;;   (define (try y)
;;     (if (good-enuf? y)
;;     y
;;     (try (improve y))))
;;   (try 1)
;; )
;;
;; I don't know yet how to define functions
;; inside another function definition, so I'm
;; defining the helper sub-functions
;; before the actual sqrt-h function

;; wait, there's really no "average" or "mean" function in Clojure?
(defn avg [& coll] (/ (apply + coll) (count coll)) )

;; "helpers"
(def tolerance 0.00001)

;; FIXME Math/abs throws exception?
(defn good-enuf? [y x]
  (< (Math/abs (- (* y y) x)) tolerance))
(defn improve [y x] (avg (/ x y) y))
;
;

(defn trythis [y x]
  (if (good-enuf? y x)
    y
    (trythis (improve y x) x
             )))

;; calculate square root
(defn sqrt-h [x] (trythis 1 x))
