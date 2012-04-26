tms
===

Turing Machine Simulator

Usage
-------

To run the turing machine simulator:

    $ java tm.Simulator tm/input

where `input` has the next format:

    aaaa
    q1, a -> q2, b, R
    q1, b -> q1, b, R
    q1, # -> qf, #, L

The first line is the string input and the next
lines are the transitions. The states are labeled starting
from `q1`. The final state is `qf`.
The blank symbol is `#`

If you want to run the turing machine step by step
add the `s` argument:

    $ java tm.Simulator tm/input s
