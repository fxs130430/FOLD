:- discontiguous(duration/2).
:- discontiguous(wage_inc_f/2).
:- discontiguous(wage_inc_s/2).
:- discontiguous(wage_inc_t/2).
:- discontiguous(working_hours/2).
:- discontiguous(standby_pay/2).
:- discontiguous(shift_differential/2).
:- discontiguous(holidays/2).
:- discontiguous(cola_none/1).
:- discontiguous(cola_tcf/1).
:- discontiguous(cola_tc/1).
:- discontiguous(pension_none/1).
:- discontiguous(pension_ret_allw/1).
:- discontiguous(pension_empl_contr/1).
:- discontiguous(educ_allw_yes/1).
:- discontiguous(educ_allw_no/1).
:- discontiguous(vacation_ba/1).
:- discontiguous(vacation_avg/1).
:- discontiguous(vacation_gnr/1).
:- discontiguous(lngtrm_disabil_yes/1).
:- discontiguous(lngtrm_disabil_no/1).
:- discontiguous(dntl_ins_none/1).
:- discontiguous(dntl_ins_half/1).
:- discontiguous(dntl_ins_full/1).
:- discontiguous(bereavement_yes/1).
:- discontiguous(bereavement_no/1).
:- discontiguous(empl_hplan_none/1).
:- discontiguous(empl_hplan_half/1).
:- discontiguous(empl_hplan_full/1).
:- discontiguous(good_contract/1).
:- discontiguous(bad_contract/1).
foil_numeric_predicates([duration/2, wage_inc_f/2, wage_inc_s/2, wage_inc_t/2, working_hours/2, standby_pay/2, shift_differential/2, holidays/2]).
foil_predicates([cola_none/1, cola_tcf/1, cola_tc/1,pension_none/1, pension_ret_allw/1, pension_empl_contr/1, educ_allw_yes/1, educ_allw_no/1, vacation_ba/1, vacation_avg/1, vacation_gnr/1, lngtrm_disabil_yes/1 , lngtrm_disabil_no/1, dntl_ins_none/1, dntl_ins_half/1, dntl_ins_full/1, bereavement_yes/1, bereavement_no/1, empl_hplan_none/1, empl_hplan_half/1, empl_hplan_full/1]). 
foil_cwa(true).
foil_use_negations(false).
foil_det_lit_bound(0).
duration(p0,1).
wage_inc_f(p0,50).
working_hours(p0,40).
shift_differential(p0,2).
holidays(p0,11).
vacation_avg(p0).
bereavement_yes(p0).
good_contract(p0).
duration(p1,2).
wage_inc_f(p1,40).
wage_inc_s(p1,50).
working_hours(p1,35).
pension_ret_allw(p1).
educ_allw_yes(p1).
holidays(p1,11).
vacation_ba(p1).
dntl_ins_full(p1).
empl_hplan_full(p1).
good_contract(p1).
working_hours(p2,38).
pension_empl_contr(p2).
shift_differential(p2,5).
holidays(p2,11).
vacation_gnr(p2).
lngtrm_disabil_yes(p2).
dntl_ins_half(p2).
bereavement_yes(p2).
empl_hplan_half(p2).
good_contract(p2).
duration(p3,3).
wage_inc_f(p3,30).
wage_inc_s(p3,40).
wage_inc_t(p3,50).
cola_tc(p3).
educ_allw_yes(p3).
bereavement_yes(p3).
good_contract(p3).
duration(p4,3).
wage_inc_f(p4,40).
wage_inc_s(p4,40).
wage_inc_t(p4,50).
working_hours(p4,40).
holidays(p4,12).
vacation_avg(p4).
dntl_ins_half(p4).
bereavement_yes(p4).
empl_hplan_half(p4).
good_contract(p4).
duration(p5,2).
wage_inc_f(p5,20).
wage_inc_s(p5,20).
working_hours(p5,35).
shift_differential(p5,6).
educ_allw_yes(p5).
holidays(p5,12).
vacation_avg(p5).
good_contract(p5).
duration(p6,3).
wage_inc_f(p6,40).
wage_inc_s(p6,50).
wage_inc_t(p6,50).
cola_tc(p6).
pension_empl_contr(p6).
holidays(p6,12).
vacation_gnr(p6).
lngtrm_disabil_yes(p6).
dntl_ins_none(p6).
bereavement_yes(p6).
empl_hplan_half(p6).
good_contract(p6).
duration(p7,3).
wage_inc_f(p7,60).
wage_inc_s(p7,40).
wage_inc_t(p7,20).
working_hours(p7,40).
shift_differential(p7,3).
holidays(p7,12).
vacation_ba(p7).
good_contract(p7).
duration(p8,2).
wage_inc_f(p8,30).
wage_inc_s(p8,70).
working_hours(p8,38).
standby_pay(p8,12).
shift_differential(p8,25).
educ_allw_yes(p8).
holidays(p8,11).
vacation_ba(p8).
lngtrm_disabil_yes(p8).
dntl_ins_half(p8).
bereavement_yes(p8).
good_contract(p8).
duration(p9,1).
wage_inc_f(p9,50).
cola_none(p9).
working_hours(p9,40).
pension_empl_contr(p9).
shift_differential(p9,4).
holidays(p9,11).
vacation_gnr(p9).
lngtrm_disabil_yes(p9).
dntl_ins_full(p9).
good_contract(p9).
duration(p10,3).
wage_inc_f(p10,30).
wage_inc_s(p10,40).
wage_inc_t(p10,40).
cola_none(p10).
working_hours(p10,36).
shift_differential(p10,3).
holidays(p10,13).
vacation_gnr(p10).
bereavement_yes(p10).
empl_hplan_full(p10).
good_contract(p10).
duration(p11,2).
wage_inc_f(p11,60).
wage_inc_s(p11,60).
working_hours(p11,38).
shift_differential(p11,4).
holidays(p11,15).
dntl_ins_full(p11).
good_contract(p11).
duration(p12,2).
wage_inc_f(p12,30).
wage_inc_s(p12,40).
cola_none(p12).
working_hours(p12,40).
shift_differential(p12,2).
educ_allw_no(p12).
holidays(p12,10).
vacation_ba(p12).
lngtrm_disabil_no(p12).
dntl_ins_half(p12).
empl_hplan_half(p12).
bad_contract(p12).
duration(p13,3).
wage_inc_f(p13,30).
wage_inc_s(p13,40).
wage_inc_t(p13,50).
cola_tcf(p13).
working_hours(p13,37).
shift_differential(p13,4).
holidays(p13,13).
vacation_gnr(p13).
dntl_ins_full(p13).
bereavement_yes(p13).
empl_hplan_full(p13).
good_contract(p13).
duration(p14,1).
wage_inc_f(p14,30).
cola_none(p14).
working_hours(p14,36).
shift_differential(p14,10).
educ_allw_no(p14).
holidays(p14,11).
vacation_gnr(p14).
good_contract(p14).
duration(p15,2).
wage_inc_f(p15,40).
wage_inc_s(p15,40).
cola_none(p15).
working_hours(p15,37).
pension_empl_contr(p15).
holidays(p15,11).
vacation_avg(p15).
dntl_ins_full(p15).
bereavement_yes(p15).
good_contract(p15).
duration(p16,1).
wage_inc_f(p16,20).
working_hours(p16,35).
shift_differential(p16,2).
holidays(p16,12).
vacation_ba(p16).
good_contract(p16).
duration(p17,1).
wage_inc_f(p17,20).
cola_tc(p17).
working_hours(p17,40).
pension_ret_allw(p17).
standby_pay(p17,2).
shift_differential(p17,3).
educ_allw_no(p17).
holidays(p17,9).
vacation_ba(p17).
lngtrm_disabil_yes(p17).
dntl_ins_half(p17).
empl_hplan_none(p17).
bad_contract(p17).
duration(p18,1).
wage_inc_f(p18,20).
cola_none(p18).
working_hours(p18,38).
pension_none(p18).
educ_allw_yes(p18).
holidays(p18,11).
vacation_avg(p18).
lngtrm_disabil_no(p18).
dntl_ins_none(p18).
bereavement_no(p18).
empl_hplan_none(p18).
bad_contract(p18).
duration(p19,2).
wage_inc_f(p19,40).
wage_inc_s(p19,50).
cola_tcf(p19).
working_hours(p19,35).
standby_pay(p19,13).
shift_differential(p19,5).
holidays(p19,15).
vacation_gnr(p19).
good_contract(p19).
duration(p20,2).
wage_inc_f(p20,40).
wage_inc_s(p20,40).
working_hours(p20,38).
shift_differential(p20,4).
holidays(p20,12).
vacation_gnr(p20).
dntl_ins_full(p20).
empl_hplan_full(p20).
good_contract(p20).
duration(p21,2).
wage_inc_f(p21,20).
wage_inc_s(p21,30).
working_hours(p21,40).
pension_none(p21).
holidays(p21,11).
vacation_ba(p21).
bad_contract(p21).
duration(p22,3).
wage_inc_f(p22,30).
wage_inc_s(p22,40).
wage_inc_t(p22,40).
cola_tcf(p22).
working_hours(p22,27).
good_contract(p22).
duration(p23,2).
wage_inc_f(p23,40).
wage_inc_s(p23,40).
working_hours(p23,40).
shift_differential(p23,4).
holidays(p23,10).
vacation_gnr(p23).
dntl_ins_half(p23).
empl_hplan_full(p23).
good_contract(p23).
duration(p24,1).
wage_inc_f(p24,60).
working_hours(p24,38).
standby_pay(p24,8).
shift_differential(p24,3).
holidays(p24,9).
vacation_gnr(p24).
good_contract(p24).
duration(p25,3).
wage_inc_f(p25,20).
wage_inc_s(p25,20).
wage_inc_t(p25,20).
cola_none(p25).
working_hours(p25,40).
pension_none(p25).
holidays(p25,10).
vacation_ba(p25).
dntl_ins_half(p25).
bereavement_yes(p25).
empl_hplan_full(p25).
bad_contract(p25).
duration(p26,2).
wage_inc_f(p26,40).
wage_inc_s(p26,40).
cola_tcf(p26).
educ_allw_yes(p26).
holidays(p26,10).
vacation_ba(p26).
lngtrm_disabil_yes(p26).
dntl_ins_none(p26).
empl_hplan_half(p26).
good_contract(p26).
duration(p27,2).
wage_inc_f(p27,30).
wage_inc_s(p27,30).
cola_none(p27).
working_hours(p27,33).
educ_allw_yes(p27).
holidays(p27,12).
vacation_gnr(p27).
bereavement_yes(p27).
empl_hplan_full(p27).
good_contract(p27).
duration(p28,2).
wage_inc_f(p28,50).
wage_inc_s(p28,40).
cola_none(p28).
working_hours(p28,37).
shift_differential(p28,5).
educ_allw_no(p28).
holidays(p28,11).
vacation_ba(p28).
lngtrm_disabil_yes(p28).
dntl_ins_full(p28).
bereavement_yes(p28).
empl_hplan_full(p28).
good_contract(p28).
duration(p29,3).
wage_inc_f(p29,20).
wage_inc_s(p29,20).
working_hours(p29,35).
pension_none(p29).
holidays(p29,10).
vacation_avg(p29).
bereavement_yes(p29).
empl_hplan_full(p29).
bad_contract(p29).
duration(p30,3).
wage_inc_f(p30,40).
wage_inc_s(p30,40).
wage_inc_t(p30,50).
cola_none(p30).
working_hours(p30,40).
educ_allw_no(p30).
holidays(p30,11).
vacation_avg(p30).
dntl_ins_half(p30).
good_contract(p30).
duration(p31,3).
wage_inc_f(p31,30).
wage_inc_s(p31,20).
wage_inc_t(p31,20).
cola_tc(p31).
working_hours(p31,40).
pension_none(p31).
shift_differential(p31,5).
educ_allw_no(p31).
holidays(p31,10).
vacation_ba(p31).
lngtrm_disabil_yes(p31).
dntl_ins_half(p31).
bereavement_yes(p31).
empl_hplan_full(p31).
bad_contract(p31).
duration(p32,2).
wage_inc_f(p32,20).
wage_inc_s(p32,20).
working_hours(p32,38).
pension_empl_contr(p32).
holidays(p32,10).
vacation_avg(p32).
bad_contract(p32).
duration(p33,2).
wage_inc_f(p33,40).
wage_inc_s(p33,50).
cola_none(p33).
working_hours(p33,40).
pension_none(p33).
shift_differential(p33,3).
educ_allw_no(p33).
holidays(p33,10).
vacation_ba(p33).
lngtrm_disabil_no(p33).
dntl_ins_none(p33).
empl_hplan_none(p33).
bad_contract(p33).
duration(p34,3).
wage_inc_f(p34,20).
wage_inc_s(p34,20).
wage_inc_t(p34,20).
cola_tc(p34).
working_hours(p34,40).
pension_none(p34).
standby_pay(p34,2).
shift_differential(p34,1).
educ_allw_no(p34).
holidays(p34,10).
vacation_ba(p34).
lngtrm_disabil_no(p34).
dntl_ins_half(p34).
bereavement_yes(p34).
empl_hplan_full(p34).
bad_contract(p34).
duration(p35,2).
wage_inc_f(p35,20).
wage_inc_s(p35,20).
cola_none(p35).
working_hours(p35,40).
pension_none(p35).
educ_allw_no(p35).
holidays(p35,11).
vacation_avg(p35).
lngtrm_disabil_yes(p35).
dntl_ins_none(p35).
bereavement_yes(p35).
empl_hplan_full(p35).
bad_contract(p35).
duration(p36,1).
wage_inc_f(p36,20).
cola_tc(p36).
working_hours(p36,40).
pension_ret_allw(p36).
standby_pay(p36,4).
shift_differential(p36,0).
educ_allw_no(p36).
holidays(p36,11).
vacation_gnr(p36).
lngtrm_disabil_no(p36).
dntl_ins_none(p36).
bereavement_no(p36).
empl_hplan_none(p36).
bad_contract(p36).
duration(p37,1).
wage_inc_f(p37,20).
cola_none(p37).
working_hours(p37,38).
pension_empl_contr(p37).
standby_pay(p37,2).
shift_differential(p37,3).
educ_allw_no(p37).
holidays(p37,9).
vacation_ba(p37).
lngtrm_disabil_yes(p37).
dntl_ins_half(p37).
empl_hplan_none(p37).
bad_contract(p37).
duration(p38,3).
wage_inc_f(p38,20).
wage_inc_s(p38,20).
wage_inc_t(p38,20).
working_hours(p38,37).
pension_empl_contr(p38).
holidays(p38,10).
vacation_avg(p38).
bereavement_yes(p38).
empl_hplan_none(p38).
bad_contract(p38).
duration(p39,2).
wage_inc_f(p39,40).
wage_inc_s(p39,40).
cola_none(p39).
working_hours(p39,40).
shift_differential(p39,4).
holidays(p39,12).
vacation_avg(p39).
lngtrm_disabil_yes(p39).
dntl_ins_full(p39).
bereavement_yes(p39).
empl_hplan_half(p39).
good_contract(p39).


h_bad_contract(X) :- pension_none(X).
h_bad_contract(X) :- empl_hplan_none(X).
h_bad_contract(X) :- lngtrm_disabil_no(X).
h_bad_contract(X) :- pension_empl_contr(X),vacation_avg(X),not(h_ab0(X)).

h_ab0(X) :- dntl_ins_full(X).

error(C):-
               findall(M,(h_bad_contract(X),\+ bad_contract(M)),L1),length(L1,C1),
			   findall(M,(bad_contract(X), \+ h_bad_contract(X)),L2),length(L2,C2),
			   C is C1+C2.


% h_good_contract(X) :- dntl_ins_full(X).
% h_good_contract(X) :- vacation_gnr(X),working_hours(X, A), A =< 39.0.
% h_good_contract(X) :- cola_tcf(X).
% h_good_contract(X) :- educ_allw_yes(X),bereavement_yes(X).
% h_good_contract(X) :- empl_hplan_half(X),bereavement_yes(X).
% h_good_contract(X) :- vacation_avg(X),dntl_ins_half(X).
% h_good_contract(X) :- shift_differential(X, A), A =< 5.0,vacation_avg(X).
% h_good_contract(X) :- educ_allw_yes(X),not(h_ab0(X)).
% h_good_contract(X) :- vacation_gnr(X),dntl_ins_half(X).
%h_good_contract(X) :- vacation_ba(X),not(h_ab1(X)).

% h_ab0(X) :- bereavement_no(X).
% h_ab1(X) :- educ_allw_no(X).

% error(C):-
%               findall(M,(h_good_contract(X),\+ good_contract(M)),L1),length(L1,C1),
%			   findall(M,(good_contract(X), \+ h_good_contract(X)),L2),length(L2,C2),
%			   C is C1+C2.
