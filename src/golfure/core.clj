(ns golfure.core)
"There be dragons."

(defmacro F
  [& forms]
  (if (or (-> forms first symbol? not)
          (-> forms count (= 2)))
   `(F ~'i ~@forms) ; Defaulting to start symbols from i
   (let [[first-sym & seqs] (butlast forms)
          syms (->> first-sym name first int
                    (iterate inc)
                    (map  (comp symbol str char))
                    (take (count seqs))
                    vec)]
     `(for [~syms (map list ~@seqs)]
        ~(last forms)))))

(assert (= (F (range 3) (* i 2)) '(0 2 4)))
(assert (= (F [1 2 3] [30 20 10] (/ (+ i 1) (+ j 1))) '(2/31 1/7 4/11)))
(assert (= (F x (range 3) "ABC" {x y})))


(defmacro G
  [& forms]
  (if (or (-> forms first symbol? not)
          (-> forms count (= 2)))
   `(G ~'i ~@forms) ; Defaulting to start symbols from i
   (let [[first-sym & seqs] (butlast forms)
          syms (->> first-sym name first int
                    (iterate inc)
                    (map  (comp symbol str char))
                    (take (count seqs)))]
     `(for [~@(interleave syms seqs)]
        ~(last forms)))))

(assert (= (G [1 2 3] [30 20 10] [i j]) '([1 30] [1 20] [1 10] [2 30] [2 20] [2 10] [3 30] [3 20] [3 10])))
(assert (= (G x (range 1 4) "ABC" (repeat x y) z)) '(\A \B \C \A \A \B \B \C \C \A \A \A \B \B \B \C \C \C))
(assert (= (G (range 4) (range 4) (range (max i j)) k) '(0 0 1 0 1 2 0 0 0 1 0 1 2 0 1 0 1 0 1 0 1 2 0 1 2 0 1 2 0 1 2 0 1 2)))


(defmacro H [& forms] `(fn ~(vec (butlast forms)) ~(last forms)))

(assert (= (map (H i j (str i " &" j)) [1 2 3] [30 20 10]) '("1 &30" "2 &20" "3 &10")))


(defmacro golfure-eval [& forms]
  `(let [~'A range ~'R reduce ~'E reductions ~'M map ~'S sort ~'Sb sort-by ~'V reverse ~'I inc ~'D dec
         ~'It iterate ~'Re repeatedly ~'Y cycle
         ~'P apply ~'C conj ~'O assoc  ~'w :when ~'N count
         
         ~'K take ~'D drop ~'B butlast ~'L last ~'Fi filter ~'G group-by
         ~'T partition ~'Tb partition-by ~'Sa split-at ~'Cc concat ~'In interpose
         
         ~'J clojure.string/join ~'Rs read-string ~'Re clojure.string/replace ~'J clojure.string/join
         ~'Fr frequencies
         
         ~'s str ~'n not ~'t set ~'m nth ~'f first ~'e second ~'o comp ~'p juxt
         
         ; I'm not sure if lists or vectors are a better type here.
         ; With vectors you don't need to use `nth` or `get`.
         ~'Tp ~(fn [& args] (apply map list args))]
     ~@forms))

(assert (= (golfure-eval (p f (o N e)) (G f (F (Y "ABC") (A 10) [i j])))
           {\A [[\A 0] [\A 3] [\A 6] [\A 9]], \B [[\B 1] [\B 4] [\B 7]], \C [[\C 2] [\C 5] [\C 8]]}))

