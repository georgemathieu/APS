#!/usr/bin/env bash
src/APS0/prologTerm $1 | swipl -s prolog/typeChecker.pl -g main_stdin