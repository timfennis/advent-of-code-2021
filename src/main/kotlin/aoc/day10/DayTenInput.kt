@file:Suppress("unused")

package aoc.day10

import aoc.splitLines

internal val dayTenExampleInput = """
    [({(<(())[]>[[{[]{<()<>>
    [(()[<>])]({[<{<<[]>>(
    {([(<{}[<>[]}>{[]{[(<()>
    (((({<>}<{<{<>}{[]{[]{}
    [[<[([]))<([[{}[[()]]]
    [{[{({}]{}}([{[{{{}}([]
    {<[[]]>}<{[{[{[]{()[[[]
    [<(<(<(<{}))><([]([]()
    <{([([[(<>()){}]>(<<{{
    <{([{{}}[<[[[<>{}]]]>[]]
""".trimIndent().splitLines()

internal val dayTenInput = """
    {<[[<<<[[{[[<<()()>>[([]<>){{}{}}]]]<(<{{}<>}{{}{}}><{<>[]}[{}{}]>)({(())}<[(){}][(){}]>)>}]<<<(<<{}
    {[({({([({(((<[]()>[()<>]){[<>[]](<>[])})<<<[]()}>(<()()>)>)}[{((<{}[]>{<>()}){[<>{}]<<><>>})(({[]()}<<>()>)<
    (((([{{{<{<[[<()<>><<>{}>]]{<(<>())>}>{<[([]<>)<{}{}>][[<><>][<>[]]]>[{(<>{})[<>()]}<<()[]>[[]<
    <<[<([[{{{[({<<>()>}<<<>[]>([]())>){{{{}[]}<(){}>)}]{{[(()())[(){}]]<<[]{}>({}[])>}({<<><>>}<([]<>){()[]}>)}
    <[(({[({<<[[[{<>{}}(<><>))<(<>[])[{}[]]>]<[([][])[(){}]]>]([<[()()](<>[])>]<(<{}()>[{}<>])({<>[
    (<[[{[[[(<((<<{}{}><[]<>>><[{}<>][<>[]]>)<(<{}<>><{}{}>)[{()[]}<<>()>]>)>[(<{({}{})}[{()[]}{[]<>}]>){<(([]
    (([[({{{(<([({[]{}}){{[][]]}])>)[<([[[{}]{(){}}][([]())]]([{[][]}{<>()}](({}()){<>{}})))[{({
    {(<<([([(<<[{((){})([]<>)}]<{{{}{}}{{}[]}}<{(){}}{()[]})>><([<[]{}>([]<>)](<()<>>)){<([]<>
    {[<<[<{{((({<[[]{}]{{}}>{<{}<>>[<>[]]}})<{[(<><>><{}<>>][{[]{}}]}[[[[][]]<<>{}>]{[[]()][[]<>]}]>)[(
    ({<(<([{<{<[(((){}){(){}})]>}[{{{{()()}<[]()>}{{<>()}<{}{}>}}[[{{}{}}[<><>]]{({}[]>[[]{}]}]}({{{()()
    {[[{([{<<{<(<<{}()>[()]>)<{{()()}}[<[][]>(<>{})]>>{[<{<>()){()<>}>{{()()}[<>()]}][[[{}[]]]<{{}[]}<()(
    {{<<<<<[[([{<[{}[]]{()<>}>{<<>()>{{}<>}}}<<{{}()}{()()}>{[()<>]({}[])]>]{[(<{}{}>)<({}[])>]<{({}<>)}{({}())
    ({(<<{[<<{<(<[<>()][[][]]><[{}<>]({}[])>)[[[<>[]][[]()]]<{[]{}}[{}{}]>]>{[(<[]()>{<>{}})][<{{}}<{
    (<<<[[({<[[(<<[]{}>>[(()<>)[<>{}]])<{(<>[])}(<<>()>[[]{}])>]<[({[]()}{{}{}})<{{}()}<{}<>>>]>][[({<<>()>{[]()}
    {{{[<[([<(<{[([][])[{}[]]]{[()()]{{}()}}}><[<([][])(<>())><{()[]}({}<>)>]<<<(){}><<><>>>((()()){
    [(((([{({<(<(<[]<>>{()[]})><{(<><>){{}[]}}{(()<>)([][])}>)>})}][({{[[{<(<><>){{}()}>((<>()){<>[]
    ([{<{<({(<[(<({}<>)><{[]}[{}[]]>)(<[[][]]{[]{}}>{{[]}((){})})]{(((()){{}()})(([][])({}{})))[(<()[]><(
    [([{{<{(<[{{((()())<[]{}>){{{}<>}<<><>>}}[<([]())>[(()[])<{}{}>]]}{[({(){}}{(){}])<<[]()>{{}()}>
    (<[<{{[<<({<<{<><>}><<()[]>[<><>]>>}{[(((){}){()<>}}<<[]()>{(){}}>]{{{{}[]}[{}<>]}[{{}{}}<(){}>]}}){{({
    <([([[<[({<<{{[][]}<{}[]>}<<()[]>[()<>]>>>})((<(<[[]]((){})>{([]()){<><>}})>)(<<<<[]<>>[[]()])>>([[
    [<<{<(<<{{[({<<>()><{}[]>})<{[<>()](<><>)}[{{}()}{[]()}]>]}}({<[{<()<>>}[<<>>]]((<[][]>{[][
    {{(<({[((<([({()<>})[{{}[]}(()<>))]{[{[]<>}][{()<>}]})([({<><>}{{}()})])>)({<(((()<>)(<><>)))>((
    [(<{({[<{<[<([{}()](<>{}))>]([([[]<>]<()<>>){<()>}]{{[[]()]{{}{}}}[{[]}{{}<>}]})>}<([<[<<>[]>]{<(){}>{
    [[[[<(<({<[[<{()}{{}[]}><[<><>]<()[]>>](<([]{})[{}]>)]>}<[{[[{(){}}]{(<><>)}][<<[][]>{(){}
    {(({({[{{((<[([]())((){})]>){[{(<>[])}<([]{})[[]<>]>]({<()<>>[[]<>]}[<()>{<>[]}])})({<([[][]]((){}))([<>{}])
    <(<(<<{[<<{<<[()[]][[]()]><([][])<{}()>>}}>[<<<<{}<>>>{[{}][{}{}]}>[<[()()]{{}[]}>[{[][]}([])]]>[[<<[][]>(
    <<{{({<<(([{{([][])(()<>)}}(<<[]{}>>)])([{[(()[])]}<{{<><>}<[]<>>}[<{}<>><[]<>)]>]{{<[{}[]]
    ({[[[[(([[[{<({})([]())>{{<>{}}}}][[<<[]>[<>()]>]{[[[]<>][<>{}]]{[<>[]]}})][[<(<(){}>{()[]})><[[()[
    (((((([{{{(<<(<>{}){<>}><(()[])<()[]>>>{[<{}<>>(<>())}<{{}()}>})((([(){}]{<>})[{[]{}}{{}[]
    (([([(((<[(({[<>()]{[][]}})[{{[]()>[[]{}]}(<<>{}><{}<>>)]){[({[]}(<>[]))([()()][<>{}])]({({}
    [(<({{([[<(((<<>()>[[]<>])({{}()}<()<>>))[[{[]()}<()<>>][{()()}<{}[]>]])>({(<({}{})<()[]}>[{()
    {{{[([<{{{([<<<>{}>{()<>}>{<{}()><<><>>}]([{()}]))<{[<()()>{()[]}]{({}[])[<>()]}}>}}}({<(({({
    [([([<[<(<[{{{<>()}{()[]}}({()[]}[{}<>])}{[{<>}]([[][]]<(){}>)}]>[[{<[<>()][{}()]>(((){}>[<>[]])}{{{<>(
    {{[{{[[<{(<([[[][]]([]<>)])>)<{<<{<>()}[<>()]><((){})<<><>>>>([<<><>>(()<>)]([(){}}{[]<>}))}>}(({{{{[]}{
    [[[[({[<({{[[(<><>)]{<<>[]>([]{})}]<[{[]}{()()}]([<>()]([]<>>)>}})>{{<([{<{}{}>{()}}])({<[<>()](<><>)>
    {[({<[({[{[({[{}()][[]<>]}((()<>)<{}<>>))<[{{}}({}{})]>]{[<<<>[]>[[][]]>(({}[])(()[]))>{(([]{})({}[]))([
    [<<([{{(<<[<[[{}[]]](<[]{}>[<>{}])>[[<{}[]>(<>[])]{([][])[()[]]}]][[{{()[]}[<>{}]}(<<>{}>[[]
    <[(([{([([<{[[(){}]<<>{}>]}[[[()()]{{}<>}]]>[[{({}{})[()()]}([()()][<>[]])]{<[[]<>][[][]]>}]]{{<{[<>
    [<[[[[([[{[[<[{}{}]([]<>)>[({}[])<[]<>>]][<{()()}[<>[]]>([[]]<<><>>)]]}([<{{{}[]}}{(()<>>({}[
    <[<{[(<{({[<[[[][]]([][])]<([]){{}<>}>>[({<>{}}<[]>)]]([[([]{})[[]()]][[[]{}]({}{})]](<[{}<>
    ({[<([(<[[<([[(){}]<<>{}>])({[[]{}]<()[]>}<<[]<>>{[][]}>)>{<[<[]()><{}[]>]([<>()]({}[]))><(
    ({<<<{<({[[{[{<>{}}[<><>]]}{(({})[()()])<<()<>>([]{})>}]]]{<{{([[]<>](<>[]))[[{}()]({}())]}}>((<([{}<>])>{{<[
    [{([{[[{{<{<[{[]()}<[]{}>]>({<{}[]>{<>[]}})}[(({{}<>}<{}<>]){[[]{}]({}[])})]>}}]({([[([<()()>{[]
    <[{([([{[{<(([<>[]]{<>()})<{{}<>}>){([<><>])<(()[])<<>{}>>}><{{[{}<>]{<>}}[(())[()<>]]}<{[{}{}]{{}{}}}<{{}<>
    [{<<([{{(((<{<<>()>[{}()]}>{<<<>[]><[]()>>}){[(([][])([]()))<{{}[]}[[]()]>][[{{}<>}({}{})]{({}{})(<
    {({[((<([[[{{([])[()()]}({{}{}}))<(<[]<>>[()[]])>]<<([[]<>])<[<>()][[]<>]>>({[<><>]{{}[]}}({()[]
    {[(<(<{[({[(<<[]>{<>{}}>[{<><>}([]())])(<[[][]]<()<>>>[(()[])])]<{[{()[]}[<>{}]]}[{<(){}>(<>{})}<<<>()>>]
    (<{<[<({{{{([({}{})[[]{}]]({{}<>}<()[]}))}(<<{<>()}{()<>}>{[<>{}][()[]]}>{<<[][]>{<>()}><(<>{})<{}[]>>})}
    {(<<{[({<<[(<{{}{}}<[]<>>>((<>())([]<>)))]>><<({<{[]<>}([]<>)>{[()()]}}<<{{}<>}<()()>>(<()()
    <([([<(<[({[[({}()){()<>}](({}{})(<><>))][{({}<>)<{}{}>}{([]){(){}}}]})]>)<[[{<{{(()){<>[]}}({{}()}
    <<<([{[({{{((<{}()>{()()})[[<>()]])[[[{}()]<{}()>]{{[]<>}{{}())}]}<<{<<>><<><>>}[{{}[]}(<><>)
    <([<[<{[[[{[{[<><>][{}<>]}(<[]{}>)]}([{{<>()}<{}[]>}{([]<>)[(){}]}]<{((){})<[]()>}[<[]()><()[]>]>
    [([[[[[({{[(<[{}<>]<{}()>><[<><>]{<>()}>)]}<{(<(()<>)<{}{}>>{{()<>}(()()}})}>})(<[<([<<>[]>((){})]([[]<>]<()
    {(<[[{(((([[([{}()]<{}()))[({}{}){[]}]](<<<>[]>>([<>{}]))][(<({}{})<()[]>><<[]{}><{}[]>>)])(<([([][])[
    ({(([{{<{{{<[<<>[]>{()<>}][([][]){()<>}]>([<()>(<><>)]<{<><>}<[]>>)}<{[[<>[]][<>{}]]<{<>()}{[][]
    {[([[[(([<{<{[[]<>]<<>()>}<<()()>([]{})>>[<<()[]>(<>())>[(()<>)]]}>]{{({{{{}[]}({})}<{{}<>}[[]()]>}((
    [<([[<[{{{[[[<<>{}>]{<()[]>{[]<>}}]{([[]{}]({}()))}]}}}(<{<[[({}{})<()()>]]({({}<>)[[]]}{[<><>]{<>{}}})>(<[
    <<(<<{{(({<[<<[]{}>{()()}}[[()[]]]]{[(<>{}){<>}]{[{}](<>)}}><{<(()())<[]<>>>[<(){}>(<>[])]
    {<(<[<[(((<{((()[])([]<>))([[]<>]({}[]))}><<([<>())<[]<>>)(<{}[]>({}<>))>[[([]())]({()()}{{}<>})
    [[((<[[[{{<{<{{}()}[{}()]>{{[]{}}[<><>]}}>}<{[<[(){}]<<><>>]([{}<>]<<><>>)]}(<<[{}<>]<<>{}>
    <[{{[[{<[{(({[(){}]({}{})})[([{}<>][()()])[<[][]><{}<>>]])}](<({(([]<>){()[]})[([]())((){})]}{{{<>{}}<[]()>}<
    <{((<([[{<<[(<{}()><{}[]>)]>[([{<>()}<()()>]<{()[]><()[]>>){<[{}[]]>[{[][]}(<><>)]}]>{{[{{{}<>}{<>}}[[(){}]<(
    {<[({(<{{[([{{[]{}}((){})}[[{}[]]<{}()>]]((<[]<>>)<([]()){{}[]}>))(<<{()[]}{[]})>[[(()())]{[()<>][<>(
    <<{[[[[{<({<[[{}()]]{{[]<>}{[]}}><{{{}()}({}{})}>}{([({}[])({}())](({}[])))[<{[]<>}[<>()]><<[]<>>(<>[])>]}
    [(([<<[{[{<({{()[]}<<><>>})[[([]())[[]{}])(<<>{}>)]>(<{{[][]}[{}()]}([{}{}][(){}])>((<<>[]>(()
    [({<<[[<{[<<({[]()}<()<>>)>]]{{<[[[]][{}<>]]>}{([[<>{}][{}{}]]<((){})(()[])>)}}}([{((<{}[]>)[<{}[]><{}()>])}
    {[[((<[{<<([{<<><>>([]{})}<(<><>)<<>()>>][{[[]{}]{()()}}<({}{})([][])>])><<{({<>{}}[<>()])((
    [[[<{{(<((([{<[]{}>({}<>)}[<<>()>]]<<(()[]){{}[]}>{(()<>){{}<>}}>){{{(()())[<><>]}[[[]<>]{<>[]}]}}))[
    (<({[({[[({{<{<>{}}<()[]>>[{(){}}<<>[]>]}[[<[][]><()<>>]({<>{}}{[]()})]}{(([[]<>][{}[]])(<[]<>>([
    {([<{[<({{{({[[][]][()()])[<<>()><[][]>])}<{{{[]()}[{}()]}{{<>()}{<>}}}((({}())<{}{}>){(<>{})[<>{}]})>}})(((
    ([[[[[[{(((((<<>()>[[]<>])<{()()}[()<>]>){<[()[]]<<>[]>>(<<>{}}{{}[]})}))<<[([()()][[]()])[{()[]}([]())]][<([
    ({{((((<{<[{[(<>{})][<()()>[[]()]]}]{({(<>())[[]()]}{<[][]><[][]>})}>}>{<<{{(({}())[<>])((()[])<[]<>>)}}[
    <(<[[[<<{([<<[[]<>]{<>[]}><{()()}([]<>)>>]{<{(<>())((){})}{[{}{}]<<>{}>}>})({([[{}{}]])[(<<>()>[
    {[(<{<[[{<{{(<()[]><<>{}>){[()<>]<{}<>>}}[[[<>{}][[]<>]]]}<[[({})<<>[]>]]{{<()<>><<>[]>}<{()[]}((){}
    ({[{(<<{[<{({[()()]{{}}}<({}{}){()()}>)}<{(({}{})[<>[]]){([]())[[][]]}}{(<[]<>)[()])[[<>()]]}>>
    (({<<[<[<<<({<{}[]>({}{})}{<{}[]><{}[]>})>>(<<(<()()>{[]{}})({()<>}[()()])>[{[{}()][[]]}({<>()}}]>(
    <<(<(<[{([{<[[<>{}]<{}<>>]>{<(()[])(()())><{<>[]}{()()}>}}([{({}<>){<>{}}}{{<>[]}[{}{}]}]([<[]()>((){})
    {[{([({[[<<<{{[]{}}<[]{}>}[<()[]><[]<>>]>([{{}[]][[][]]]{{(){}}({}[])})>(<{[<>{}](()<>)}>{[<[]()>
    ([[[<(<{[({<({()()}{<>()})((<>[])<[]<>>)>}<{<<{}()><<>>>}({{{}[]}}[(()[])[()<>]])>)<[<((()())[<>{}])(
    {<(((<[((((({<[]>[[]{}]}))[(<[[][]][{}{}]>){{[<>()][[]<>]}([()()](<><>))}])){(<[{[<>()][{}()]
    ({([{<(((<{[<[{}[]]<<>>>({{}[]}[[]()])]([<(){}>])}{<[{<>[]}{[]<>}][<<><>){<>{}}]>[<((){})([]{})>[[{}{}][()
    <[{[<[{[({{<(({}<>}[[]()])>({{[][]}}<[(){}](()[])>)}})([[{{<<>()>([]{})}[<<>{}>[<><>]]}]])]
    {(<{([{[[({[(<[]{}>)({{}[]}{()()}))<{(()[])}[[()()]{{}[]}]>}<{<(()<>)>{{[]{}}({}())}}({{{}[]}{<
    [<<{<{<<(<([<(<>()){[]()}>[({}<>)({}<>)]][(([]{}){()[]})[{{}()}(()())]])>{([{{()[]}<<>[]>}<{<>[
    {{[[(([([({([([]<>)(()())][{<>()}{(){}}])})])]([{{[<{{{}<>}(<>())>{[()()]<<>[]>}>[{(()){{}[
    [({({({(({{(<[()()][{}<>]>[[{}()]{(){}}])<[<<>[]>][<{}>(<>{})]>}(((({}())({}{}))){{(()[])<[][
    <(<(<[([<[{[<(()<>)<()[]>><(<>())([][])>]{<[{}<>]{{}()}>{[[]{}](<>{})}}}<[<([]{})><{{}<>}([]())>]>]>
    <<<(<<[({{{<{({}<>)<()[]>}<[()<>]{(){}}>><{[[]()]<[]{}>}[({}{})<(){}>]>}(<[<<>[]>{()[]}]>)}[<{[<{}>[{}{
    {(<((<[{{[[[[<{}{}]<{}()>]][{({}{})<<>{}>}[<()[]>(()())]]]{([<[]()><[]{}>](<{}()>))({<<><>>(<>())}{
    {({{[[<[{<{{(<<>>)(<[]()>[()])}([({}<>)])}[[{[()<>][()[]]}]({<[]<>>{<>()}})]><<(<<{}()>{<>[]
    {{{[{[<<{[[{[{{}<>}<[][]>]{(()[])<<>()>}}[<{()()}[(){}]><<()[]>>]]{<((<>{})({}()))[<(){}>([][])]>{({{}[]}<
    <{{[<{({{{[<{{<>()}{{}()}}>]<{<[()[]](<><>)><[<>[]]{(){}}>}<([(){}]<{}{}>){(())}>>}[([{[[]<>][{}<>]}[{()}(
    [<<[{{(({({<{{{}{}}{<>[]}}({[][]}{<>[]})>[{[{}<>][<>[]]}[({}[])[[]<>]]]})}))<(<[[<(({}()))[<<>()>{(
    {<<([<{({[{{[({}[])<<>{}>]([()<>]<(){}>)}<([{}[]]({}{}))>}[<<<<>{}>[[][]]>{<<>[]><()>}>[<<<>><[][]>
    [({(<({[<[{{[<<>[]>[()[]]]<<<>[]>>}}<{([{}[]](<>{}))[{<>}{{}<>}]}(<<[][]>>[{[]<>}])>]>[(<(({<>[]}{<><>}))(<
    {<[[{{<<{(<(<([]<>)[[][]]>[([]{})[<>{}]])<[{<>()}{[]<>}]>>>[{(({()()}[<>[]]){({}())<[]<>>})}[(<{<>
    {[[{{{<[<[{<(({}{})[{}()])[(<><>)({}())]>}{[<{[]{}}[()[]]>]<{[()()]<[]()>}<[[][]]>>}]>{{({{{()[]}{{}(
    <{[<([{{[[<(<{<><>}[{}{}]><((){})>){[[()()][()[]]]{[[]<>]}}>]<<(<({}[])<{}[]>>{(<><>)<[]()>}){(<
""".trimIndent().splitLines()