**Sequence Alignment**
This is a Java program for performing sequence alignment between two strings using dynamic programming.

**Overview**
Sequence alignment is a fundamental problem in bioinformatics and computational biology. Given two sequences, the goal is to identify the optimal way to align them by inserting gaps ('-') and matching characters. This implementation utilizes dynamic programming to efficiently compute the optimal alignment and associated alignment score.

**Features**
Dynamic Programming: The alignment is computed using a dynamic programming approach, allowing for efficient solution finding.
Customizable Costs: Mismatch costs are customizable based on whether characters are the same, vowels, consonants, or a mix of vowels and consonants.
Alignment String Generation: The program generates the aligned sequences along with gap symbols to represent insertions and deletions.
**Usage**
To use this program, simply create an instance of the SequenceAlignment class and provide the two sequences to align. You can then retrieve the alignment string using the getAlignment() method.
**Customization**
You can customize the costs associated with mismatches by modifying the constants in the SequenceAlignment class constructor. For example, you can adjust the costs for matching characters, mismatches between vowels, mismatches between consonants, and mismatches between vowels and consonants.
