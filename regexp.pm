#!/usr/bin/perl

print "Hello World\n";

$_ = "yabba dabba doo";

if(/abba/) {
    print "it matched\n";
}

if(/yab.a/) {
    print "matched dot-all character\n";
    # * + ? 
}

if(/ya(.)\1/) {
    # /y((.)(.)\3\2) d\1/
    print "matched captured characters\n";
}
# |, [0-9], [^0-9]
# \d \s \w  \D \S \W

my $string = "I saw Barnny\nbowling\nlast night with fred.";
if($string =~ /Barnny.*fred/s) {
    print "matched multi-line\n";
}

if($string =~ /bowling$/m) {
    print "matched bowling at the end of the line\n";
}

my $string1 = "Hi there,beighbor";

if($string1 =~ /\s([a-zA-Z]+),/) {
    print "I found $1\n";
}

my $names = "Fred and Barney";
if($names =~ /(\w+) (and|or) (\w+)/) {
    print "$1 and $2\n";
    # (?<name1>\w+) -> $+{name1}
}

########################################

# s/with (\w+)/against $1's team/

my $home = "home,sweet home";
$home =~ s/home/cave/; # g
print $home;

# /fred.+barney/ -> fred and barney went bowling last night #greedy\
# but the speed still depends on the String content.

# \G matches last match ending position,useful for recursive.
# if \G match failed,go to the start of the string.
# s/\Gx?/!/g -> !abcde

my $string4 = "abcde";
$string4 =~ s/x?/!/g;
print $string4;
# # s/\Gx?/!/g -> !abcde\

# lookaround doesn't match nay characters,but match a position
# (?=\d) the current position is followed by a digit.
# (?<=\d) the current position is preceded by a digit.

my $jeff = "Jeffs";
$jeff =~ s/Jeff(?=s\b)/Jeff'/;
print $jeff; # jeff's

my $fred = "Freds";
$fred =~ s/(?<=\bFred)(?=s\b)/'/g;
print $fred; # Fred's
# we did not match any characters but position,we matched a position
# where we want to insert the character.

my $digit = 1453454564565;
$digit =~ s/(?<=\d)(?=(?:\d\d\d)+$)/,/g;
print $digit;


my $digit2 = " my salary is 49845449849841644894 per month\n";
print "\n";
$digit2 =~ s/(\d)(?=(?:\d\d\d)+(?!\d))/$1,/g;
print $digit2;
