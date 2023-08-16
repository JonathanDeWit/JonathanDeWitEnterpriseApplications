package com.example.jonathandewitenterpriseapplications;

import com.example.jonathandewitenterpriseapplications.models.Product;
import com.example.jonathandewitenterpriseapplications.repository.IProductRepository;
import com.example.jonathandewitenterpriseapplications.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DatabaseInitializer {

    @Autowired
    private IProductRepository productRepository;

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            if (productRepository.count() == 0) { // If products table is empty
                productRepository.save(new Product("LG Split Airco - S09ET", "Inverter Compressor\nDe Inverter Compressor van LG lost de onjuiste en ineffectieve werking en geluidsproblemen op. Dat levert een airconditioner op die sneller koelt, duurzamer is en stiller werkt.\nEnergy Saving\nDe inverter compressor in de airconditioner is zo ingesteld dat deze de draaisnelheid aanpast aan de temperatuur. Dit zorgt ervoor dat er geen energie wordt verspild.\nFast Cooling\nLG air conditioner koelt de lucht extra snel zodat de ruimte sneller op temperatuur komt.\nSmart Thinq\nControle over uw airconditioner via Wi-Fi\nSimpel en Strak design met verborgen display\nLG airconditioners zijn strak en modern vormgegeven. Het design is makkelijk schoon te maken en het grote verborgen display zorgt ervoor dat het energy display goed zichtbaar is.\nLaag Geluidsniveau 19dB\nLG airconditioners opereren dankzij LG's BLDC motor technologie op uiterst laag geluidsniveau. Onnodige geluiden worden geëlimineerd waardoor de airconditioner nauwelijks te horen is.\nControle over uw energieverbruik\nMet de LG Active Energy Control-functietoets kunt u het energieverbruik in drie opties aanpassen (80%, 60%, 40%). Met slechts één druk op de knop kunt u de koelprestaties controleren en het stroomverbruik verminderen.\nOnmiddellijke setup Comfort Air\nDeze optie zet openingen van de airconditioner op een vooraf ingestelde positie, zodat de uitgaande lucht niet direct op de aanwezigen in de ruimte wordt gericht.\nFilter met dubbele bescherming\nHet filter met DUBBELE bescherming vangt stofdeeltjes én bacteriën.\nJet Cool\nHet geoptimaliseerde design van de luchtuitlaat biedt een krachtigere luchtstroom die de kamer snel afkoelt.\nAutomatisch reinigen\nDe uitgebreide automatische reinigingsfunctie verhindert de vorming van bacteriën en schimmel op de warmtewisselaar en biedt zo een aangenamere omgeving met meer comfort voor de gebruiker.\nBlaasrichting in 6 standen\nGebruikers kunnen de richting van de schoepen kiezen uit zes vooraf ingestelde posities om te voldoen aan de exacte blaasrichting die u wenst.\nDraaifunctie in vier richtingen\nLG-airconditioners leveren koele lucht in elke hoek van de kamer. De zwenkfunctie in 4 richtingen blaast de lucht snel en efficiënt in meerdere richtingen.", BigDecimal.valueOf(625.99), "Airco", 50, "LG_Spirit.jpg"));
                productRepository.save(new Product("TCL Split Airco TAC-09/XA71", "Productkenmerken:\nWiFi-bediening\nMet de WiFi module in het apparaat kunt u de airconditioner overal via uw telefoon bedienen.\nAutomatische modus\nSelecteert automatisch de benodigde ventilatorsnelheid om de ingestelde temperatuur te bereiken of te handhaven.\nZelfdiagnose\nIn het geval van een storing voert het apparaat zelfdiagnose uit en geeft het de juiste foutcode weer, zodat het probleem nauwkeurig wordt gedefinieerd en de reparatie zelf sneller en goedkoper is.\nAutomatisch opnieuw opstarten\nIn het geval van een plotselinge stroomstoring, herstelt de automatische herstartfunctie de laatst gewenste persoonlijke instellingen na het herstarten van het apparaat.\nEcologische factor\nAfgevuld met R32. Kies voor een R-32-product om uw milieu-impact te reduceren met 68% in vergelijking met R-410A-systemen en rechtstreeks uw energieverbruik te reduceren, dankzij het hoge energierendement.\nEnergiebesparend.\nDankzij het intelligente algoritme bereikt het apparaat sneller, preciezer en soepeler de gewenste temperatuur.\nAnti-koude functie\nBij het omschakelen van koele naar warme lucht zal de ventilator pas in werking treden als de temperatuur in de verdamper de gewenste waarde bereikt. Dit voorkomt dat koude lucht aan het begin van de werking van het apparaat wordt geblazen.", BigDecimal.valueOf(470.49), "Airco", 45, "TCL_Split.jpg"));
                productRepository.save(new Product("AGN AGN12MIS-3.5KW", "Merk\tAGN\nType\tairconditioning Split Pared 1x1\nThermostaat\tJa\nEnergieklasse\tA++\nKoelvermogen\t12 000\nIdeaal oppervlak\t35m²\nLuchtdebiet (m³/u)\t540\nVerwarmingsfunctie\tJa\nOntvochtiger (L/24u)\t1\nAantal snelheden\t4\nProgrammeerbaar\tJa\nGeluidsniveau\t55 dB\nAanvullende beschrijving\tWifi en option,\nfonction Ifeel, réversible, autonettoyante, et mode nuit,\nAfmetingen product\tH 80,5 cm x L 28,5 cm x D 19,4 cm\nAfmetingen pakje\tH 30 cm x L 90 cm x W 90 cm\nNettogewicht\t31 kg\nBrutogewicht\t35 kg\nArtikelcode\t969968", BigDecimal.valueOf(349.95), "Airco", 40, "AGN_AGN12MIS.jpg"));
                productRepository.save(new Product("Blueqon BF60LX", "Blueqon BF60LX Statiefventilator – Wit\nMet de Blueqon BF60LX in huis ben je voorbereid op warme, zomerse dagen. De staande ventilator heeft een diameter van 45cm. De korf is kantelbaar, waardoor je de stroom van koele lucht heel gericht bepaalt. En gebruik je de oscillatiefunctie, dan zwenkt de ventilator in een boog van 75 graden van links naar rechts, waardoor hij de koele lucht nog beter door de ruimte verspreidt.\n\nDeze ventilator is robuust en stabiel dankzij de hoogwaardig koper gewikkelde motor en de krachtige aerodynamische bladen zorgen ervoor dat de luchtstroom moeiteloos wordt geblazen.\n\nHet statief is in hoogte verstelbaar tussen de 101 en 121 cm. De ventilator heeft drie snelheden (langzaam, normaal, snel).\nKenmerken:\n-Snelheidschakelaar (0-1-2-3)\n-Hoogwaardig koper gewikkelde motor\n-Krachtige aerodynamische ventilatorbladen\n-Zwenkfunctie van 75 graden\n-In hoogte verstelbaar (101 - 121cm)\n-Service & garantie: 2 jaar Nederlandse fabrieksgarantie", BigDecimal.valueOf(34.95), "Ventilator", 100, "Bluegon.jpg"));
                productRepository.save(new Product("AirKing - Statiefventilator", "Zoek je een stille ventilator die je in hoogte kan verstellen, te bedienen is met een afstandsbediening, een automatische timerfunctie heeft en er ook nog eens écht mooi uitziet? De complete AirKing AKSF-40BK ventilator voorziet in al je wensen!\n\nExtra stil en roterend\n\nDoor de speciale vorm van de bladen is deze ventilator stiller dan een gemiddelde ventilator. Dit is natuurlijk extra fijn als je hem wilt gebruiken in de slaapkamer of tijdens het werken op een warme dag. Daarnaast is het mogelijk om de ventilator te laten roteren van links naar rechts zodat je een fijne luchtstroom creëert.\n\n40 cm en afstandsbediening\n\nDe ventilatorbladen zijn 40 centimeter groot en daarmee is deze ventilator ideaal om grotere ruimtes te verkoelen. De ventilator kent maar liefst 5 blaasstanden en is kantelbaar in verschillende richtingen. Je bedient hem eenvoudig met de bedieningsknoppen of met de bijbehorende afstandsbediening (werkt met 2 stuks AAA batterijen, deze zijn niet inbegrepen).", BigDecimal.valueOf(59.99), "Ventilator", 70, "AirKing_Statiefventilator.jpg"));
                productRepository.save(new Product("JAP Quebec", "Ben je op zoek naar een stille torenventilator? Dan is de JAP Bionic Breeze Quebec wat je nodig hebt. Met deze stille torenventilator koel je namelijk jouw favoriete kamer in een mum van tijd. Daarnaast volstaat het design ook nog eens in ieder interieur.\n\nKrachtig maar stil\n\nDe torenventilator zorgt dankzij de nieuwste technologie voor een continue stroom aan frisse lucht, en dit in maximale stilte. Handig voor wanneer je de ventilator bijvoorbeeld in de slaapkamer wilt gebruiken.\n\nDe alleskunner\n\nDe torenventilator is voorzien van 3 rotatiesnelheden om de intensiteit van de luchtstroom in te stellen zoals jij dat wilt. Een lage snelheid voor in de nacht, een gemiddelde snelheid en een turbo. Dit gebeurt allemaal door middel van een exclusieve motor van 100% koper met een hoge efficiëntie en betrouwbaarheid.\n\nOscillerende functie\n\nDe torenventilator beschikt over een oscillerende functie waardoor deze onder een hoek van 65° van links naar rechts draait. De torenventilator oscilleert voor een betere luchtverspreiding, dus om een groter gebied te kunnen koelen.", BigDecimal.valueOf(44.95), "Ventilator", 100, "JAP_Quebec.jpg"));
                productRepository.save(new Product("Honeywell HT900E4", "Honeywell HT900E4 vloerventilator\nDiameter: 18 cm\nKleur: zwart\nVermogen: 40 Watt\nGeluidsniveau: 39 dB\nTurboForce vermogen voor intense koeling of gebruik als lucht circulatiepomp voor energiebesparing\n25 % stiller dan vergelijkbare ventilatoren\n3 krachtige snelheden\nVerwijderbaar rooster voor eenvoudige reiniging\nKan worden gemonteerd aan de wand om ruimte te besparen\nVolledig zwenkbaar en te draaien tot een hoek van 90 graden\nLuchtstroom: 740 m3/uur", BigDecimal.valueOf(34.00), "Ventilator", 30, "Honeywell_HT900E4.jpg"));
                productRepository.save(new Product("Duux Whisper", "Maak kennis met de ventilator die je verrast met een zacht briesje, een sterke windvlaag en alles daar tussenin. De Duux Whisper heeft maar liefst 26 snelheden, dus er zit altijd een snelheid voor jou tussen. En het maakt niet uit waar in de kamer je je bevindt, want dankzij de horizontale en verticale oscillerende functie brengt hij overal een aangename koelte.\n\nJouw voordelen\n26 instelbare snelheden waaronder natuurlijke bries en nachtstand\nIn hoogte verstelbaar van 76 tot 95cm\nFluisterstil (13dB in stand 1)\n90⁰ horizontale en 100⁰ verticale swing\nBereik windstroom van 15 meter\nInclusief afstandsbediening\nMeerdere snelheden en richtingen\nWhisper doet zijn naam eer aan en is een van de stilste ventilatoren van zijn soort. Het kleine beetje geluid dat wordt geproduceerd lijkt op de wind die je buiten hoort ritselen. Je kunt eenvoudig schakelen tussen verschillende windsnelheden en richtingen van de luchtstroom. De ventilator is in hoogte verstelbaar en draait zowel naar links en rechts als naar boven en beneden. Zo wordt de koele lucht gelijkmatig door de ruimte verdeeld.", BigDecimal.valueOf(129), "Ventilator", 35, "Duux_Whisper.jpg"));
                productRepository.save(new Product("Dyson HP00 Pure", "Met de Dyson HP00 Pure Hot + Cool Luchtreiniger reinig je niet alleen de lucht in jouw huis, maar verwarm je deze tegelijkertijd. Fijn, want zo heb je geen koude luchtstroom in je kamer. De luchtreiniger beschikt over 10 standen, waaronder een automatische modus. Met de automatische modus meet de Dyson luchtreiniger met verschillende sensoren de luchtkwaliteit en past hierop zijn werking aan.\n\nGebruik deze Dyson luchtreiniger ook 's nachts met de speciale nacht modus. Zo behoudt jij de kwaliteit van jouw lucht, zelfs als je slaapt.\n\nDrievoudige functionaliteit\nHet unieke van de Dyson Hot+ Cool Luchtreiniger is de drievoudige functionaliteit. Deze luchtreiniger zorgt ervoor dat jouw lucht het hele jaar door gereinigd wordt, verwarmt de lucht met thermostaatregeling op de koude winterse dagen en verkoelt de lucht in de zomer.\n\nHEPA-filter\nHet HEPA en het koolstoffilter zuiveren vieze geurtjes, virussen, allergenen en stofjes uit de lucht. Dit filter zorgt ervoor dat het stof veilig en betrouwbaar wordt opgesloten. Hierdoor blijft de uitblaaslucht schoon en vrij van onaangename geurtjes, virussen en allergenen.\n\nLet op: staat de luchtreiniger 24 uur per dag aan, dan dien je de filters na 6 maanden te vervangen.", BigDecimal.valueOf(499.99), "Ventilator", 50, "Dyson_HP00_Pure.jpg"));
                productRepository.save(new Product("Travellife TL32", "Genieten van een gekoeld drankje op de camping? Of wil je een maaltijd warmhouden tijdens een dagje weg? Met de Travellife TL32 thermo-elektrische koelbox kan het allebei! Door het compacte formaat en de bijgeleverde autoplug is de koelbox ideaal voor zowel thuis, onderweg als op de bestemming.\n\nStel de koelbox in op Eco en bespaar stroom\n\nMet de Travellife TL32 koelbox houd je je drink- en etenswaren altijd koud of warm. Kies eenvoudig tussen de twee standen door de knop om te schuiven. Op de koude stand koelt de thermo-elektrische koelbox tot 20 °C onder de omgevingstemperatuur en op de warme stand verwarmt de koelbox tot een temperatuur van 50 °C. Daarnaast kan je de koelbox instellen op Eco, wat een stroombesparing van 1,5 A oplevert. Door de handige draaghendel en het lichte gewicht neem je de koelbox eenvoudig mee. Met de draaghendel kan je ook de deksel geopend vastzetten, zodat je gemakkelijk een drankje kunt pakken. Dit maakt de koelbox ideaal voor op de camping of een dagje weg, maar ook voor in je eigen tuin.", BigDecimal.valueOf(73.99), "Koelbox", 8, "Travellife_TL32.jpg"));
                productRepository.save(new Product("Curver Koelbox - 32L - Antraciet", "Deze Curver Koelbox 32 liter is ideaal voor een dagje uit! De koelbox is multifunctioneel: hij kan ook gebruikt worden als zitje! Het deksel gebruik je als dienblad of tafel. Gebruik hem voor een dagje strand, een picknick of voor op de camping.\n\nDeze koelbox heeft dubbele wanden en is voorzien van een isolerende laag. Deze koelbox is geschikt voor het meenemen van o.a. 1,5L flessen. Door het hengsel omlaag te doen wordt de deksel vastgezet Het formaat met een inhoud van 32 liter is prima handelbaar. Het deksel is bevestigd met scharnieren, waardoor het gemakkelijk te openen en te sluiten is. Let op: koelelementen worden niet meegeleverd!\n\nCurver Koelbox 32L\n\nDubbele wanden\nVoorzien van een isolerende laag\nOok te gebruiken als zitje en het deksel als dienblad of tafel.\nGeschikt voor het meenemen van 1,5 liter flessen\nHet hengsel sluit de koelbox af\nGemakkelijk te reinigen\nHandig in gebruik", BigDecimal.valueOf(15.99), "Koelbox", 5, "Curver_Koelbox.jpg"));
                productRepository.save(new Product("Koelelementen 400ml (6 stuks)", "6 stuks Koelelementen met 400 ml vulling per unit.\nDoordat deze 35mm dik zijn, behoud deze langdurig bevriezing in tegenstelling tot de dunnere meest verkrijgbare koel elementen.\n\nVoor een optimale werking dient u de koelelementen BOVEN op uw producten in een koelbox of koeltas te leggen.\nKoude daalt, waar de warme stijgt!!\n\nVoor ijs en ijskoude dranken enz...\nVoor 10 uur koeling\nKorting bij 2 of 3 x 6stuks afname", BigDecimal.valueOf(15.99), "Koelelementen", 20, "Koelelementen.jpg"));
                productRepository.save(new Product("20 STUKS ROZE mini Koel elementen 100 ML", "20 Stuks mini koel elementen a 100ML Roze.\n\nHandig om overal tusssen uw producten te stoppen voor een ultieme koeling.\n\n100 ml per stuk\nKleur Roze.\n\nIn 4 van kleuren verkrijgbaar.\nBlauw, Lichtblauw, Roze, en Lichtroze\n\nFSC* C148090", BigDecimal.valueOf(19.99), "Koelelementen", 25, "RozeKoelelementen.jpg"));
            }
        };
    }
}