(ns golfure.examples)
"There be dragons."

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; This is a verbose method to initialize your Clojure environment:
(let [to-hex (fn [data] (.substring (Integer/toString (+ (bit-and data 0xff) 0x100) 16) 1))
      clj    (-> "https://raw.githubusercontent.com/nikonyrh/golfure/v0.1.1/src/golfure/core.clj" slurp str)
      
      ; Well this is a bit clumsy, alternatively you could use:
      ; $ curl -s https://raw.githubusercontent.com/nikonyrh/golfure/v0.1.1/src/golfure/core.clj | sha1sum
      hash   (->> clj
                  .getBytes
                  (.digest (java.security.MessageDigest/getInstance "sha1"))
                  (map to-hex)
                  clojure.string/join)]
  
  ; This won't give you any real security, but at least you can confirm that the
  ; contents are the same as what I used when writing this version of the code.
  (assert (= hash "31c242360d5acf53d4f6449e2660c2a3f0c92f44") (format "The hash is %s!" hash))
  
  ; `subs` skips the namespace definition, yet an other hack.
  (load-string (subs clj 39))
  "Loaded successfully!"

  (assert (= (F (range 3) (* i 2)) '(0 2 4)))
  (assert (= (golfure-eval (M (H i j (str i " & " j)) "ABC" [1 2 3])) '("A & 1" "B & 2" "C & 3")))
  "Assert OK!")


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Here is a oneliner:
(do (-> "https://raw.githubusercontent.com/nikonyrh/golfure/master/src/golfure/core.clj" slurp (subs 39) load-string)
    "OK!")


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Porting some of my older Clojure answers:
; https://codegolf.stackexchange.com/users/59617/nikonyrh?tab=answers
;
; `golfure-eval` is not part of the answer's byte count, it is just calling
; the generic interpreter the same way you'd be passing a data structure to `eval`.
;
;;;;;;;;;;;
; https://codegolf.stackexchange.com/a/108719/59617
;
(def f               #(or(apply <=(map int %))(apply >=(map int %))))
(def f               #(let[c(map int %)a apply](or(a <= c)(a >= c))))

(def f (golfure-eval #(let[c(M int %)](or(P <= c)(P >= c)))))
(def f (golfure-eval #(or(P <=(M int %))(P >=(M int %)))))

;;;;;;;;;;;
; https://codegolf.stackexchange.com/a/117469/59617
; TODO: Add aliases for `min` and `max`, although they are so short already
; TODO: Add :when support for F and G
; TODO: Add destructuring support for F and G, althoug I'm not sure if it can be
;         distinquished from "sequence expressions". Maybe with a special indicator.
;
(def f               (fn f[s](if(=()s)0(+(apply min(for[i(range(count s))[a b][(split-at(inc i)s)]:when(=(reverse a)a)](f b)))1))))
(def f (golfure-eval (H :g s(if(=()s)0(+(A min(for[i(A(N s))[a b][(Sa(inc i)s)]:when(=(V a)a)](f b)))1)))))

;;;;;;;;;;;
; https://codegolf.stackexchange.com/a/106594/59617
;
; TODO: Do not count % as a symbol in F and G on #( ... ) forms
; TODO: Learn more about `for` macros, wow my F and G don't nest at all!
;
(def f               #(for[a %](for[b(apply map list %2)](map str a b))))
(def f (golfure-eval #(for[a %](for[b(P Tp %2)](M s a b)))))

