:-dynamic ab0/1.
:-dynamic flies/1.
:-dynamic penguin/1.

foil_numeric_predicates([age/2]).
foil_numeric_predicates([age/2]).
foil_predicates([flies/1, penguin/1, bird/1, cat/1]).                                
foil_cwa(true).                                            
foil_use_negations(false).                                 
foil_det_lit_bound(0).

bird(X) :- penguin(X).
bird(b1).
bird(b2).
bird(b3).
bird(b4).
bird(b5).
bird(b6).
bird(b7).

age(b1,1.0).
age(b2,2.0).
age(b3,3.0).
age(b4,4.0).
age(b5,5.0).
age(b6,6.0).
age(b7,7.0).

age(c5,5.0).
age(c2,2).

cat(c1).
cat(c2).
cat(c5).
penguin(p1).
penguin(p2).

flies(b5).

