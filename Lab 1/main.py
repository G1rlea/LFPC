from automata.fa.nfa import NFA

nfa = NFA(
    states={'S', 'B', 'D', '$'},
    input_symbols={'a', 'b', 'c', 'd'},
    transitions={
        'S': {'a': 'S', 'b': 'B'},
        'B': {'c': 'B', 'a': 'D', 'd': '$'},
        'D': {'b': '$', 'a': 'B'},
        '$': {}
    },
    initial_state='S',
    final_states={'$'}
)
string = input()
if nfa.accepts_input(string):
    print("POSSIBLE")
else:
    print("NOT POSSIBLE")