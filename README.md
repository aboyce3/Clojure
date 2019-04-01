# Clojure
Conversion to NAND logic


Converts logical expressions which consist of “and”, “or”, and “not” connectives to ones which only use the “nand” connective. These follow 
the rules of:
(not x) =: (nand x)

(and x y) =: (nand (nand x y))

(and x y z) =: (nand (nand x y z))

(and w x y z) =: (nand (nand w x y z))

(or x y) =: (nand (nand x) (nand y))

(or x y z) =: (nand (nand x) (nand y) (nand z))

(or w x y z) =: (nand (nand w) (nand x) (nand y) (nand z))




This program consumes an unevaluated list which could look like this: 
(and false (or false (and y (not true))))

Then converts the list to:
(nand (nand false (nand (nand false) (nand (nand (nand y (nand true)))))))

Which would then simplify to:
False

This program works for any unevaluated list.
