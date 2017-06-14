foil_numeric_predicates([humidity/2, temperature/2]).
foil_predicates([sunny/1, overcast/1, rainy/1, windy/1, no_wind/1]).                                
foil_cwa(true).                                            
foil_use_negations(false).                                 
foil_det_lit_bound(0).

play(d1).
play(d5).
play(d6).
play(d7).
play(d8).
play(d9).
play(d12).
play(d13).
play(d14).

dont_play(d2).
dont_play(d3).
dont_play(d4).
dont_play(d10).
dont_play(d11).
sunny(d1).
sunny(d2).
sunny(d3).
sunny(d4).
sunny(d5).
overcast(d6).
overcast(d7).
overcast(d8).
overcast(d9).
rainy(d10).
rainy(d11).
rainy(d12).
rainy(d13).
rain(d14).
temperature(d1,75).
temperature(d2,80).
temperature(d3,85).
temperature(d4,72).
temperature(d5,69).
temperature(d6,72).
temperature(d7,83).
temperature(d8,64).
temperature(d9,81).
temperature(d10,71).
temperature(d11,65).
temperature(d12,75).
temperature(d13,68).
temperature(d14,70).
humidity(d1,70).
humidity(d2,90).
humidity(d3,85).
humidity(d4,95).
humidity(d5,70).
humidity(d6,90).
humidity(d7,78).
humidity(d8,65).
humidity(d9,75).
humidity(d10,80).
humidity(d11,70).
humidity(d12,80).
humidity(d13,80).
humidity(d14,96).
windy(d1).
windy(d2).
windy(d6).
windy(d8).
windy(d10).
windy(d11).
no_wind(d3).
no_wind(d4).
no_wind(d5).
no_wind(d7).
no_wind(d9).
no_wind(d12).
no_wind(d13).
no_wind(d14).

% h_play(X) :- overcast(X).
% h_play(X) :- temperature(X, A), A =< 75,not(ab0(X)).
% ab0(X) :- windy(X),rainy(X).
% ab0(X) :- humidity(X, A), A >= 95,sunny(X).
