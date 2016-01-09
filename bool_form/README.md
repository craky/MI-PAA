Text z https://edux.fit.cvut.cz/courses/MI-PAA/homeworks/05/start 
# Řešení problému vážené splnitelnosti booleovské formule pokročilou iterativní metodou

## Problém

Je dána booleovská formule F proměnnných X=(x1, x2, … , xn) v konjunktivní normální formě (tj. součin součtů). Dále jsou dány celočíselné kladné váhy W=(w1, w2, … , wn). Najděte ohodnocení Y=(y1, y2, … , yn) proměnných x1, x2, … , xn tak, aby F(Y)=1 a součet vah proměnných, které jsou ohodnoceny jedničkou, byl maximální.

Je přípustné se omezit na formule, v nichž má každá klauzule právě 3 literály (problém 3 SAT). Takto omezený problém je stejně těžký, ale možná se lépe programuje a lépe se posuzuje obtížnost instance (viz Selmanova prezentace v odkazech).

## Poznámka

Obdobný problém, který má optimalizační kritérium ve tvaru „aby počet splněných klausulí byl maximální“ a kde váhy se týkají klausulí, se také nazývá problém vážené splnitelnosti booleovské formule. Tento problém je lehčí a lépe aproximovatelný. Oba problémy se často zaměňují i v seriózní literatuře.

## Příklad

x1' značí negaci x1.

n = 4 
F = (x1 + x3' + x4).(x1' + x2 + x3').(x3 + x4).(x1 + x2 + x3' + x4').(x2' + x3).(x3' + x4') 
W = (2, 4, 1, 6)

Přípustné konfigurace, kde F=1 (řešení):

X = {x1 … xn} = {0, 0, 0, 1}, S = 6 
X = {x1 … xn} = {1, 0, 0, 1}, S = 2 + 6 = 8 (optimální) 
X = {x1 … xn} = {1, 1, 1, 0}, S = 2 + 4 + 1 = 7 
Tato instance v DIMACS CNF formátu

c Priklad CNF
c 4 promenne a 6 klauzuli
c kazda klauzule konci nulou (ne novym radkem)
p cnf 4 6
1 -3 4 0
-1 2 -3 0
3 4 0
1 2 -3 -4 0
-2 3 0
-3 -4 0

## Pokyny k řešení

Problém řešte některou z pokročilých lokálních heuristik (simulované ochlazování, genetické algoritmy, tabu prohledávání). Řešení jinými metodami prosím zkonzultovat se cvičícím nebo přednášejícím. Volby konkrétních parametrů heuristiky a jejích detailů (operace nad stavovým prostorem, kritérium ukončení, atd. atd.) proveďte sami, tyto volby pokud možno zdůvodněte a ověřte experimentálním vyhodnocením. Hodnocení Řešení této úlohy je podstatnou součástí hodnocení zkoušky (30 bodů ze 100). Hodnotí se především postup při aplikaci heuristiky, tj. postup a experimenty, jakým jste dospěli k výsledné podobě (parametry, konkrétní operátory apod.). Například, pokud máte v řešení nějaké hodně neortodoxní prvky a pokud máte jejich výhodnost experimentálně doloženou, těžko mohou vzniknout námitky. Méně významné jsou konkrétní dosažené výsledky. Nežádáme rozhodně, aby semestrální práce měla úroveň světové výzvy Centra diskrétní matematiky Rutgersovy univerzity.

Tato práce by měla sloužit jako ověření Vašich schopností používat zvolenou pokročilou iterativní metodu. Ideálním výstupem by měl být algoritmus schopný řešit co nejširší spektrum instancí s rozumnou chybou. To neznamená, že pokud se Vám některé instance „nepovedou“, je vše špatně. Důležité je, abychom viděli, že jste se aspoň snažili. Někdy to prostě nejde…

## Hodnocení 5. úlohy je rozdělené do třech základních bodů:

Algoritmus a implementace (7 b)
Byly použity techniky (procedury, datové struktury), přiměřené problému?
Obsahuje řešení sofistikovanější postupy, adaptační mechanismy?
Obsahuje řešení (ověřené, porovnané!) autorovy originální prvky?
Práce s heuristikou (13 b)
Jak autor postupoval při stanovení parametrů a modifikací heuristiky?
Jetliže postupoval systematicky, jak dobře byly navrženy experimenty během práce?
Jestliže se uchýlil k slepému prohledávání prostoru parametrů, je pokrytí tohoto prostoru dostatečné?
Pro jak širokou třídu instancí autorova implementace pracuje?
Jestliže experimentální vyhodnocení signalizuje neúspěch, snažil se autor systematicky odhalit příčinu a byly přiměřeně vyčerpány možnosti nápravy?
Experimentální vyhodnocení (10 b)
Jak autor podpořil svoje tvrzení měřením?
Má měření skutečně tu vypovídací hodnotu, jakou mu připisuje autor?
Byl dostatečně zohledněn eventuální randomizovaný způsob práce algoritmu?
Dá se z výsledků usoudit na dostatečnou iterativní sílu lokálního algoritmu?
Jestliže řešení obsahuje neobvyklé, nestandardní postupy, existuje alespoň základní srovnání se standardními postupy?
Je výsledek měření správně prezentován?
Práce, kterým zcela chybí experimentální část, nelze přijmout k hodnocení.
Neúspěch při aplikaci nějaké heuristiky nevylučuje dobré hodnocení, jestliže postup prací byl racionální a prokazuje autorovu poučenost.

Prosím neptejte se nás, co vše má být ve zprávě a co vlastně máte udělat. Představte si situaci, že Vám šéf ve firmě nakáže, abyste mu naprogramovali nástroj, který dobře řeší nějaký problém. A nic víc Vám neřekne, protože tomu sám nerozumí. Vy sami musíte zhodnotit, co je potřeba udělat, abyste ověřili, že to opravdu funguje, případně abyste byli schopni se obhájit, když to (někdy) nefunguje. Po absolvování tohoto předmětu byste sami měli vědět, jak na to.

5. úlohu je nutné odevzdat minimálně 4 dny před zkouškou. Z množstevních důvodů není možné ji kontrolovat při zkoušce.
Hodnocení úloh Vám bude včas sděleno. Hodnocení bude definitivní, bez šance na opravu (u testů je to také tak…).

## Zkušební instance

Zkušební instance lze poměrně snadno vygenerovat náhodně. Důležitým parametrem, který řídí obtížnost instancí zejména pro 3 SAT, je poměr počtu klauzulí a proměnných (viz Selman - originál - MS Office 6, PDF, doporučujeme přečíst). Váhy proměnných generujte také náhodně. Volte instance přiměřené obtížnosti. Zkušební instance DIMACS nemají váhy. Dogenerujte je sami. Tyto instance jsou značně obtížné. Existuje ale jednoduchý způsob, jak jejich obtížnost snížit: prostě je zkrátit - vypustit z nich určitý počet klauzulí.

Bohužel nemáme k dispozici žádné instance, pro které známe optimální řešení. To je způsobeno hlavně tím, že nemáme ani instance s vahami. Tudíž nebudete schopni odhadnout, jak dobře Váš algoritmus opravdu funguje. Ale nám to nevadí, důležité je, abyste nás přesvědčili, že víte, co děláte.

Na druhou stranu by se tato situace mohla změnit, když nám pomůžete. Proto prosím ke zprávě přiložte Vaše zkušební instance (i s vahami) a nejlepší řešení, kterých jste dosáhli. My si pak vybereme.

## Práce s omezujícími podmínkami a počáteční instance

Pravděpodobně není reálné pracovat pouze s přípustnými řešeními. Je obava, že stavový prostor by se tím stal značně přetržitým, se špatnou dostupností. Přípustná řešení jako počáteční konfigurace nebo populace by se dala získat z nějakého SAT řešiče (dohodněme se, že je to přípustné), ale nemyslím, že je to dobrý nápad. Heuristika by tak startovala z (možná hlubokého) lokálního minima, navíc možná obklopeného nepřípustnými konfiguracemi. Museli bychom tedy stejně heuristiku připravit na práci s nepřípustnými konfiguracemi. Pokud použijete nějakou relaxační techniku a náhodný start (populaci), řešíme vlastně variantu MAX SAT s max počtem splněných klauzulí (v případě katastrofálního selhání práce i toto je lepší než nic). Vhodnou volbou pokut relaxace bychom měli dostat uspokojivě pracující proceduru.
