(defn simplify-helper [l]
(cond
(every? true? (rest l)) false
(some false? l) true
(some true? l) (simplify-helper (remove true?  (distinct l)))
(and (= 2 (count l)) (some seq? l))
 (if (= 2 (count(nth l 1)))
 (nth (nth l 1 "No value") 1 "No value")
 (distinct l))
(and (= 3 (count l)) (some seq? l) (= 2 (count (nth (filter seq? l) 0)))) 
 (if (= (remove seq? l) (nth(filter seq? l)0))
 true
 (distinct l))
 :else (distinct l)

))

;;Does the recursion with an anonymous function and runs the helper from the inside out
(defn simplify [l]
(simplify-helper (doall(map (fn [i] 
  (if (seq? i)
  (simplify i)
  i))l))))

;;deep substitute
(defn bind-values [l m]
(map (fn [i] 
  (if (seq? i)
  (bind-values i m)
  (m i i))) 
l))

;;helper that does the conversion
(defn nand-helper [l]
(cond
(= 'not (nth l 0)) (conj (rest l) 'nand)
(= 'or (nth l 0)) 
(map (fn [i] 
(cond
(not= i 'or) (conj (list i) 'nand) 
(= i 'or) 'nand)) 
  l)
(= 'and (nth l 0)) (conj (list (conj (rest l) 'nand)) 'nand)
:else l
))

;;converts and or and not logic to nand recursively and calls on a helper
(defn nand-convert [l]
(nand-helper (doall(map (fn [i] 
  (if (seq? i)
  (nand-convert i)
  i)
  ) l))))


;;Entire evaluation
(defn evalexp [exp bindings]
  (simplify (nand-convert (bind-values exp bindings))))

(def p1 '(and x (or x (and y (not z)))))
(def p2 '(and (and z false) (or x true false)))
(def p3 '(or true a))