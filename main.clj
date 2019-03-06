(defn main [l]
(cond
(some false? (rest l)) true
(every? true? (rest l)) false
(and (some true? (rest l)) (not(every? true? (rest l)))) (conj (remove true? (rest l)) 'nand)
(and (not (some true? (rest l))) (not (some false? (rest l))))(conj (distinct (rest l)) 'nand)
))


(main '(nand false)) 
(main '(nand true))
(main '(nand x x))
(main '(nand x y))
(main '(nand x true))
(main '(nand x false))
(main '(nand true true))
(main '(nand x y true))
(main '(nand x true true))
(main '(nand true true true))
(main '(nand x y false))
(main '(nand x y z))
