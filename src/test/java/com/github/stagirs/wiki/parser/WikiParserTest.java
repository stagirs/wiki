/*
 * Copyright 2018 Dmitriy Malakhov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.stagirs.wiki.parser;

import com.github.stagirs.wiki.model.WikiPage;
import com.github.stagirs.wiki.model.WikiSection;
import com.github.stagirs.wiki.model.WikiText;
import com.github.stagirs.wiki.model.text.WikiLink;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dmitriy Malakhov
 */
public class WikiParserTest {
    
    @Test
    public void test() throws IOException{
        String content = FileUtils.readFileToString(new File("src/main/resources/xml/Пограничные округа СССР.xml"), "utf-8");
        WikiPage wiki = WikiPage.fromXml(content);
        assertEquals(wiki.getTitle().getText(), "Пограничные округа СССР");
        List<WikiText> points = wiki.getAllPoints();
        List<WikiSection> sections = wiki.getAllSections();
        assertEquals(sections.size(), 11);
        assertEquals(points.size(), 35);
        {
            WikiText wikiPageText = points.get(2);
            assertEquals(wikiPageText.getRawText().trim(), "В связи с освобождением от белой гвардии и интервентов обширной территории, создавались новые пограничные дивизии, которые по окончанию [[Гражданская война в России|Гражданской войны]] стали основой для формирования пограничных округов<ref name=\"СДМ 1918-1928\">{{книга|заглавие=Пограничные войска. Сборник материалов и документов. 1918-1928|место=М.|издательство=«Наука»|год=1973|страницы=9-14, 19-20, 28-30|страниц=928|ref=Пограничные войска. Сборник материалов и документов. 1918-1928|isbn=}}</ref>.");
            assertEquals(wikiPageText.getPatterns().size(), 0); 
            assertEquals(wikiPageText.getTags().size(), 1); 
            assertEquals(wikiPageText.getLinks().size(), 1); 
            assertEquals(wikiPageText.getTables().size(), 0); 
        }
    }
    
    @Test
    public void test1() throws IOException{
        String content = FileUtils.readFileToString(new File("src/main/resources/page/Россия"), "utf-8");
        WikiPage page = new WikiPage("Россия", content);
        List<WikiText> points = page.getAllPoints();
        List<WikiSection> sections = page.getAllSections();
        assertEquals(sections.size(), 90);
        assertEquals(points.size(), 361);
        {
            WikiText wikiPageText = points.get(10);
            assertEquals(wikiPageText.getText().trim(), "Территория России, определяемая её конституцией, составляет    км² (первое место по площади среди стран мира), что чуть меньше континента Южная Америка. Расположена полностью в Северном полушарии, бо́льшая часть территории России располагается в Восточном полушарии, лишь восточная часть Чукотского автономного округа располагается в Западном полушарии. Омывается водами Тихого и Северного Ледовитого океанов, а также Балтийским, Чёрным, Азовским морями Атлантического океана, обладая самой протяжённой береговой линией в мире (37 653 км). Россия расположена на севере материка Евразия, занимая бо́льшую часть Восточной Европы и весь север Азии. Уральские горы и Кумо-Манычская впадина  разделяют Россию на европейскую и азиатскую части.");
            assertEquals(wikiPageText.getPatterns().size(), 3); 
            assertEquals(wikiPageText.getTags().size(), 2); 
            assertEquals(wikiPageText.getLinks().size(), 19); 
        }
        {
            WikiText wikiPageText = points.get(13);
            assertEquals(wikiPageText.getText().trim(), "Сибирская платформа имеет эпиархейский возраст. С чехлом Сибирской платформы связаны крупнейшие в РФ залежи каменного угля, каменных и калийных солей, нефти и газа. с трапповыми интрузиями — медно-никелевые месторождения Норильска, а с кимберлитовой трубкой — алмазы.	 	В строении Урало-Монгольского эпипалеозойского складчатого пояса, разделяющего 2 древние платформы, выделяются области рифейской, байкальской, салаирской, каледонской и герцинской складчатости. Енисей-Саяно-Байкальская область рифейской и байкальской складчатости обрамляет Сибирскую платформу. Вдоль границы с Восточно-Европейской платформой располагается Предуральский краевой прогиб, заполненный пермскими толщами с месторождениями каменного угля на севере и калийных солей в средней части прогиба (см. Урал).");
            assertEquals(wikiPageText.getPatterns().size(), 0); 
            assertEquals(wikiPageText.getTags().size(), 0); 
            assertEquals(wikiPageText.getLinks().size(), 9); 
            assertEquals(wikiPageText.getFiles().size(), 2); 
        }
    }
    
    @Test
    public void test2() throws IOException{
        String content = FileUtils.readFileToString(new File("src/main/resources/page/ВМК"), "utf-8");
        WikiPage page = new WikiPage("ВМК", content);
        List<WikiText> points = page.getAllPoints();
        List<WikiSection> sections = page.getAllSections();
        assertEquals(sections.size(), 29);
        assertEquals(points.size(), 75);
        {
            WikiText wikiPageText = points.get(15);
            assertEquals(wikiPageText.getRawText().trim(), "{| class=\"wikitable sortable\" style=\"text-align:center;\"	|- bgcolor=\"#cccccc\"	! width=60% | [[Кафедра (подразделение вуза)|Кафедра]]	! width=30% | Заведующий [[Кафедра (подразделение вуза)|кафедрой]]	! width=10%  | Год основания	|-	| Математической физики (МФ)<ref>[https://cs.msu.ru/departments/mph Кафедра математической физики — сайт ВМК МГУ]<br>[http://mph.cmc.msu.ru/ Кафедра математической физики — сайт кафедры МФ]</ref> || [[Профессор]]<br> [[Денисов, Александр Михайлович (ученый)|А. М. Денисов]] || 1982	|-	| Вычислительных технологий и моделирования (ВТМ)<ref>[https://cs.msu.ru/departments/vtm Кафедра вычислительных технологий и моделирования — сайт ВМК МГУ]<br>[http://www.inm.ras.ru/vtm/ Кафедра вычислительных технологий и моделирования — сайт кафедры ВТМ]</ref> || [[Профессор]], [[Действительные члены РАН|академик РАН]]<br>[[Тыртышников, Евгений Евгеньевич|Тыртышников Е. Е.]] || 2004	|-	| Вычислительных методов (ВМ)<ref>[https://cs.msu.ru/departments/vm Кафедра вычислительных методов — сайт ВМК МГУ]<br>[https://vm.cs.msu.ru// Кафедра вычислительных методов — сайт кафедры ВМ]</ref> || [[Профессор]], [[Действительные члены РАН|академик РАН]]<br>[[Четверушкин, Борис Николаевич|Четверушкин Б. Н.]] || 1983	|-	| Автоматизации научных исследований (АНИ)<ref>[https://cs.msu.ru/departments/ani Кафедра автоматизации научных исследований — сайт ВМК МГУ]<br>[http://ani.cmc.msu.ru/ Кафедра автоматизации научных исследований — сайт кафедры АНИ]</ref> || [[Профессор]]<br>[[Попов, Александр Михайлович (ученый)|Попов А. М.]] || 1987	|-	| Общей математики (ОМ)<ref name=autogenerated2>[https://cs.msu.ru/departments/om Кафедра общей математики — сайт ВМК МГУ]</ref> ||  || 1973	|-	| Функционального анализа и его применений (ФАиП)<ref>[https://cs.msu.ru/departments/fa Кафедра функционального анализа и его применений — сайт ВМК МГУ]</ref> || [[Профессор]], [[Действительные члены РАН|академик РАН]]<br>[[Моисеев, Евгений Иванович|Моисеев Е. И.]] || 2008	|-	| Нелинейных динамических систем и процессов управления (НДСиПУ)<ref>[https://cs.msu.ru/departments/ndsipu Кафедра нелинейных динамических систем и процессов управления — сайт ВМК МГУ]<br>[http://ndsipu.cmc.msu.ru/ Кафедра нелинейных динамических систем и процессов управления — сайт НДСиПУ]</ref> || [[Профессор]], [[Действительные члены РАН|академик РАН]]<br>[[Емельянов, Станислав Васильевич|Емельянов С. В.]] || 1989	|-	| Исследования операций (ИО)<ref>[https://cs.msu.ru/departments/io Кафедра исследования операций]<br>[http://io.cmc.msu.ru/ Кафедра исследования операций — сайт кафедры]</ref> || [[Профессор]]		и. о. [[Васин, Александр Алексеевич|Васин А. А.]]	| 1970	|-	| Оптимального управления (ОУ)<ref>[https://cs.msu.ru/departments/oc Кафедра оптимального управления]<br>[http://oc.cmc.msu.ru/ Кафедра оптимального управления — сайт кафедры]</ref> || [[Профессор]], [[Действительные члены РАН|академик РАН]]<br>[[Осипов, Юрий Сергеевич|Осипов Ю. С.]] || 1970	|-	| Системного анализа (СА)<ref>[https://cs.msu.ru/departments/sa Кафедра системного анализа]<br>[http://sa.cmc.msu.ru/ Кафедра системного анализа — сайт кафедры]</ref> || [[Профессор]], [[Действительные члены РАН|академик РАН]]<br>[[Куржанский, Александр Борисович|Куржанский А. Б.]] || 1992	|-	| Математической статистики (МС)<ref>[https://cs.msu.ru/departments/ms Кафедра математической статистики]</ref> || [[Профессор]]<br>[[Королёв, Виктор Юрьевич|Королёв В. Ю.]] || 1970	|-	| Математических методов прогнозирования (ММП)<ref>[https://cs.msu.ru/departments/mmp Кафедра математических методов прогнозирования]<br>[http://www.machinelearning.ru/wiki/index.php?title=Mmp Кафедра математических методов прогнозирования — сайт кафедры]</ref> || [[Профессор]], [[Действительные члены РАН|академик РАН]]<br>[[Журавлёв, Юрий Иванович (математик)|Журавлёв Ю. И.]] || 1997	|-	| Математической кибернетики (МК)<ref>[https://cs.msu.ru/departments/mathcyb Кафедра математической кибернетики]<br>[http://mk.cs.msu.ru/index.php/Заглавная_страница Кафедра математической кибернетики — сайт кафедры]</ref> || [[Профессор]]<br>[[Алексеев, Валерий Борисович|Алексеев В. Б.]] ||1970	|-	| Информационной безопасности (ИБ)<ref>[https://cs.msu.ru/departments/ib Кафедра информационной безопасности]</ref> || [[Профессор]], [[Действительные члены РАН|академик РАН]]<br>[[Соколов, Игорь Анатольевич|Соколов И. А.]] || 2013	|-	| Автоматизации систем вычислительных комплексов (АСВК)<ref>[https://cs.msu.ru/departments/asvk Кафедра автоматизации систем вычислительных комплексов]<br>[http://asvk.cmc.msu.ru/ Кафедра автоматизации систем вычислительных комплексов — сайт кафедры]</ref> ||  [[Доцент]]		и. о. Власов В. К.	| 1970	|-	| Суперкомпьютеров и квантовой информатики (СКИ)<ref>[https://cs.msu.ru/departments/qi Кафедра суперкомпьютеров и квантовой информатики — сайт ВМК МГУ]<br>[http://sqi.cs.msu.su/ Кафедра суперкомпьютеров и квантовой информатики — сайт кафедры СКИ]</ref> || [[Профессор]], [[Члены-корреспонденты РАН|чл.-корр. РАН]]<br>[[Воеводин, Владимир Валентинович|Воеводин В. В.]] || 2012<ref name=autogenerated8>Приказ ректора МГУ № 372 от 5 июля 2001 года<br>[http://en.cs.msu.ru/sites/cmc/files/attachs/msu_20120911_904.pdf Приказ ректора МГУ № 904 от 11 сентября 2012 года]</ref>	|-	| Алгоритмических языков (АЯ)<ref>[https://cs.msu.ru/departments/al Кафедра алгоритмических языков]<br>[http://al.cmc.msu.ru/ Кафедра алгоритмических языков — сайт кафедры]</ref> || [[Профессор]]<br>[[Мальковский, Михаил Георгиевич|Мальковский М. Г.]] || 1970	|-	| Системного программирования (СП)<ref>[https://cs.msu.ru/departments/sp Кафедра системного программирования]<br>[https://cs.msu.ru/departments/sp Кафедра системного программирования — сайт кафедры]<br></ref>|| [[Профессор]], [[Члены-корреспонденты РАН|чл.-корр. РАН]]<br>[[Аветисян, Арутюн Ишханович|Аветисян А. И.]] || 1970	|-	| Английского языка || [[Доцент]]<br>Саратовская Л. Б. || 1990	|}");
            assertEquals(wikiPageText.getPatterns().size(), 0); 
            assertEquals(wikiPageText.getTags().size(), 0); 
            assertEquals(wikiPageText.getLinks().size(), 0); 
            assertEquals(wikiPageText.getTables().size(), 1); 
        }
        {
            WikiText wikiPageText = points.get(67);
            assertEquals(wikiPageText.getRawText().trim(), "[[Файл:Суперкомпьютер «Ломоносов» в МГУ.jpg|180px|мини|right|<small><center>[[Ломоносов (суперкомпьютер)|Суперкомпьютер «Ломоносов» в МГУ]]</center></small>]]	Академия, посвящённая [[Суперкомпьютер|суперкомпьютерам]], проводится на базе факультета ВМК МГУ ежегодно, начиная с 2010 года{{sfn|Суперкомпьютерная академия}}. В ходе проведения Академии читаются лекции по различным областям суперкомпьютерных технологий, проводятся заседания, где участники представляют результаты своих исследований в этой области и получают возможность проведения своих работ на суперкомпьютерах МГУ ([[Ломоносов (суперкомпьютер)|«Ломоносов»]], «Чебышев», [[Blue Gene|Blue Gene/P]]){{sfn|Суперкомпьютеры}}.");
            assertEquals(wikiPageText.getPatterns().size(), 2); 
            assertEquals(wikiPageText.getTags().size(), 0); 
            assertEquals(wikiPageText.getLinks().size(), 3);
            {
                WikiLink link = wikiPageText.getLinks().get(0);
                assertEquals(wikiPageText.getText().substring(link.getPos(), link.getPos() + link.getText().length()), "суперкомпьютерам");
            }
            assertEquals(wikiPageText.getFiles().size(), 1); 
        }
    }    
    
    @Test
    public void test3() throws IOException{
        String content = FileUtils.readFileToString(new File("src/main/resources/page/Москва"), "utf-8");
        WikiPage page = new WikiPage("Москва", content);
        List<WikiText> points = page.getAllPoints();
        List<WikiSection> sections = page.getAllSections();
        assertEquals(sections.size(), 66);
        assertEquals(points.size(), 220);
        {
            WikiText wikiPageText = points.get(7);
            assertEquals(wikiPageText.getRawText().trim(), "Наивысшая точка находится на [[Теплостанская возвышенность|Теплостанской возвышенности]] и составляет 255 м, самая низкая точка — вблизи [[Бесединские мосты|Бесединских мостов]], где река Москва покидает город (высота этой точки над уровнем моря составляет 114,2 м)<ref>{{cite web|url=http://moscowwalks.ru/2011/06/08/samaya-vysokaya-tochka-moskvy/|title=Самая высокая и самая низкая точки Москвы|date=2011-06-08|work=«Прогулки по Москве»|accessdate=2012-04-30|archiveurl=https://www.webcitation.org/67yANH8C4|archivedate=2012-05-27}}</ref>.");
            assertEquals(wikiPageText.getPatterns().size(), 0); 
            assertEquals(wikiPageText.getTags().size(), 1); 
            assertEquals(wikiPageText.getLinks().size(), 2); 
            assertEquals(wikiPageText.getTables().size(), 0); 
        }
        {
            WikiText wikiPageText = points.get(16);
            assertEquals(wikiPageText.getRawText().trim(), "Среднегодовое количество часов солнечного сияния — 1731 час<ref>{{cite web|date=2009-01-11|url=http://pogoda.ru.net/weathernews.php?id=3284|title=2008 год стал самым тёплым в истории Москвы (абзац 2)|publisher=pogoda.ru.net|accessdate=2009-01-11|deadlink=404}}</ref> (в среднем за период 2001—2010 составило более 1900 часов<ref>{{cite web|url=http://meteoweb.ru/cl006-4.php|title=Продолжительность солнечного сияния в Москве в 2007 г|accessdate=2009-02-21|archiveurl=https://www.webcitation.org/616Hcm2nQ|archivedate=2011-08-21}}</ref>).");
            assertEquals(wikiPageText.getPatterns().size(), 0); 
            assertEquals(wikiPageText.getTags().size(), 2); 
            assertEquals(wikiPageText.getLinks().size(), 0); 
            assertEquals(wikiPageText.getFiles().size(), 0); 
        }
    }   
    
    @Test
    public void test4() throws IOException{
        String content = FileUtils.readFileToString(new File("src/main/resources/page/МГУ"), "utf-8");
        WikiPage page = new WikiPage("МГУ", content);
        List<WikiText> points = page.getAllPoints();
        List<WikiSection> sections = page.getAllSections();
        assertEquals(sections.size(), 38);
        assertEquals(points.size(), 117);
        {
            WikiText wikiPageText = points.get(9);
            assertEquals(wikiPageText.getRawText().trim(), "<blockquote>''«править доходами Университета и стараться о его благосостоянии; учреждать вместе с профессорами науки в Университете изучение в гимназии»'', вести переписку ''«со всеми присутственными местами по делам, касающимся до Университета»''<ref>Ректоры Московского университета. Биографический словарь. Вып. II. / Сост. В. В. Ремарчук. —	— М.:МГУ, 1996. — (Справочно-информационная серия «Московский университет на пороге третьего тысячелетия»)</ref></blockquote>");
            assertEquals(wikiPageText.getPatterns().size(), 0); 
            assertEquals(wikiPageText.getTags().size(), 1); 
            assertEquals(wikiPageText.getLinks().size(), 0); 
            assertEquals(wikiPageText.getTables().size(), 0); 
        }
        {
            WikiText wikiPageText = points.get(64);
            assertEquals(wikiPageText.getRawText().trim(), "В [[2013 год]]у МГУ занял общее 244-ое место в рейтинге {{нп5|Times Higher Education World University Rankings|Times Higher Education World University Rankings|en|Times Higher Education World University Rankings}} лучших университетов мира<ref>{{cite web|url=http://www.timeshighereducation.co.uk/world-university-rankings/2013-14/world-ranking/region/europe|title=Top universities by 2013|publisher=timeshighereducation.co.uk|accessdate=2014-05-10|lang=en}}</ref>, и 57-ое место в аналогичном репутационном рейтинге в 2014 году<ref>{{cite web|url=http://www.timeshighereducation.co.uk/world-university-rankings/2014/reputation-ranking/range/51-60|title=Top universities by reputation 2014|publisher=timeshighereducation.co.uk|accessdate=2014-05-10|lang=en}}</ref>.");
            assertEquals(wikiPageText.getPatterns().size(), 1); 
            assertEquals(wikiPageText.getTags().size(), 2); 
            assertEquals(wikiPageText.getLinks().size(), 1); 
            assertEquals(wikiPageText.getFiles().size(), 0); 
        }
    } 
    
    @Test
    public void test5() throws IOException{
        String content = FileUtils.readFileToString(new File("src/main/resources/page/Ломоносов, Михаил Васильевич"), "utf-8");
        WikiPage page = new WikiPage("Ломоносов, Михаил Васильевич", content);
        List<WikiText> points = page.getAllPoints();
        List<WikiSection> sections = page.getAllSections();
        assertEquals(sections.size(), 61);
        assertEquals(points.size(), 235);
        {
            WikiText wikiPageText = points.get(7);
            assertEquals(wikiPageText.getRawText().trim(), "Ставится под сомнение именование «[[Поморы|помором]]» Михаила Васильевича Ломоносова, происходившего из семьи крестьян Куростровской волости под Холмогорами, которые только по случаю и редко занимались дальними для них морскими промыслами. Журналист Дмитрий Семушин утверждает, что «поморскость» М. В. Ломоносова — это красивый исторический миф<ref name=\"regnum1521011\">{{cite web  |url=http://regnum.ru/news/1521011.html|title=\"Поморство\" Ломоносова - миф.|subtitle= |author=Дмитрий Семушин|authorlink= |coauthors= |quote= |date=2012-04-14|format= |work= |publisher=ИА REGNUM|accessdate=2016-01-21|lang=ru|description= |deadlink= |archiveurl= |archivedate= }}</ref>. Следует иметь ввиду и размытость термина (см. [[история изучения поморов]]), которая позволяет включать в понятие широкие группы населения.");
            assertEquals(wikiPageText.getPatterns().size(), 0); 
            assertEquals(wikiPageText.getTags().size(), 1); 
            assertEquals(wikiPageText.getLinks().size(), 2); 
            assertEquals(wikiPageText.getTables().size(), 0); 
        }
        {
            WikiText wikiPageText = points.get(50);
            assertEquals(wikiPageText.getRawText().trim(), "Ломоносов был похоронен {{СС3|8.4.1765}} года<ref>[http://forum.vgd.ru/file.php?fid=185583&key=1941978378 Метрическая запись об отпевании в Исаакиевском соборе]</ref> на [[Лазаревское кладбище (Санкт-Петербург)|Лазаревском кладбище]] [[Александро-Невская лавра|Александро-Невской лавры]]. Надгробие М. В. Ломоносова, поставленное канцлером [[Воронцов, Михаил Илларионович|М. И. Воронцовым]] — стела из каррарского мрамора с латинской и русской эпитафией и аллегорическим рельефом. Мастер Ф. Медико ([[Каррара]]) по эскизу [[Штелин, Яков Яковлевич|Я. Штелина]], [[1760-е]] годы.");
            assertEquals(wikiPageText.getPatterns().size(), 1); 
            assertEquals(wikiPageText.getTags().size(), 1); 
            assertEquals(wikiPageText.getLinks().size(), 6); 
            assertEquals(wikiPageText.getFiles().size(), 0); 
        }
    } 
}
