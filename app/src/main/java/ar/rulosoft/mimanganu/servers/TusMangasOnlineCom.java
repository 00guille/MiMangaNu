package ar.rulosoft.mimanganu.servers;

import android.text.Html;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.rulosoft.mimanganu.R;
import ar.rulosoft.mimanganu.componentes.Chapter;
import ar.rulosoft.mimanganu.componentes.Manga;

public class TusMangasOnlineCom extends ServerBase {

    final static int TIMEOUT = 20000;

    public static String[] generos = new String[]{"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z", "Acción", "Artes Marciales", "Aventura", "Ciencia Ficción", "Comedia", "Deportes", "Drama", "Ecchi", "Fantasía",
            "Harem", "Histórico", "Horror", "Josei", "Magia", "Mecha", "Misterio", "Psicológico", "Recuentos de la vida", "Romance", "Seinen", "Shoujo",
            "Shonen", "Shonen-ai", "Shoujo-ai", "Sobrenatural", "Suspense", "Tragedia", "Vida escolar", "Yuri"};
    public static String[] generosV = new String[]{"http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=1",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=A", "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=B",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=C", "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=D",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=E", "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=F",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=G", "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=H",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=I", "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=J",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=K", "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=L",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=M", "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=N",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=O", "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=P",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=Q", "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=R",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=S", "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=T",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=U", "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=V",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=W", "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=X",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=y", "http://www.tumangaonline.com/listado-mangas/mangas?tipo=2&filter=Z",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Acci%C3%B3n",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Artes+Marciales",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Aventura",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Ciencia+Ficci%C3%B3n",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Comedia",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Deportes",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Drama", "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Ecchi",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Fantas%C3%ADa",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Harem",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Hist%C3%B3rico",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Horror",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Josei", "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Magia",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Mecha",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Misterio",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Psicol%C3%B3gico",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Recuentos+de+la+vida",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Romance",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Seinen",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Sh%C5%8Djo",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Sh%C5%8Dnen",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Shonen-ai",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Shoujo-ai",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Sobrenatural",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Suspense",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Tragedia",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Vida+escolar",
            "http://www.tumangaonline.com/listado-mangas/mangas?tipo=3&filter=Yuri"};

    public TusMangasOnlineCom() {
        this.setFlag(R.drawable.flag_esp);
        this.setIcon(R.drawable.tumangaonline);
        this.setServerName("TusMangasOnline");
        setServerID(ServerBase.TUSMANGAS);
    }

    @Override
    public ArrayList<Manga> getMangas() throws Exception {
        return null;
    }

    @Override
    public ArrayList<Manga> search(String term) throws Exception {
        String source = getBrowserWhitHeaders().get("http://www.tumangaonline.com/listado-mangas?tipo=1&filter=" + URLEncoder.encode(term, "UTF-8"), TIMEOUT);
        return getMangasFromSource(source);
    }

    @Override
    public void loadChapters(Manga m, boolean forceReload) throws Exception {
        if (m.getChapters() == null || m.getChapters().size() == 0 || forceReload)
            loadMangaInformation(m, forceReload);
    }

    @Override
    public void loadMangaInformation(Manga m, boolean forceReload) throws Exception {
        String source = getBrowserWhitHeaders().get(m.getPath(), TIMEOUT);
        m.setSinopsis(Html.fromHtml(getFirstMacthDefault("(<p itemprop=\"description\".+?</p></div>)", source, "Sin sinopsis")).toString());
        m.setImages(getFirstMacthDefault("src=\"([^\"]+TMOmanga[^\"]+)\"", source, ""));
        m.setFinished(!(getFirstMacthDefault("<td><strong>Estado:(.+?)</td>", source, "").contains("En Curso")));
        m.setAuthor(getFirstMacthDefault("5&amp;filter=.+?>(.+?)<", source, ""));

        ArrayList<Chapter> caps = new ArrayList<>();
        Pattern p = Pattern.compile("<h5><a[^C]+Click=\"listaCapitulos\\((.+?),(.+?)\\)\".+?>(.+?)<");
        Matcher ma = p.matcher(source);
        while (ma.find()) {
            Chapter c = new Chapter(Html.fromHtml(ma.group(3)).toString(),
                    "http://www.tumangaonline.com/index.php?option=com_controlmanga&view=capitulos&format=raw&idManga=" + ma.group(1) + "&idCapitulo="
                            + ma.group(2));
            caps.add(0, c);
        }
        m.setChapters(caps);
    }

    @Override
    public String getPagesNumber(Chapter c, int page) {
        return null;
    }

    @Override
    public String getImageFrom(Chapter c, int page) throws Exception {
        if (c.getExtra() == null || c.getExtra().length() < 2 || !c.getExtra().contains("|")) {
            if (c.getExtra() == null || c.getExtra().length() < 2) {
                getExtraWeb(c);
            }
            String source = getBrowserWhitHeaders().get(c.getExtra(), TIMEOUT);
            Pattern p = Pattern.compile("<input id=\"\\d+\" hidden=\"true\" value=\"(.+?);(.+?);(.+?);(.+?);(.+?)\"");
            Matcher m = p.matcher(source);
            String imgBase;
            String imgStrip;
            String imagenes = "";
            if (m.find()) {
                imgBase = "http://img1.tumangaonline.com/subidas/" + m.group(1) + "/" + m.group(2) + "/" + m.group(3) + "/";
                imgStrip = m.group(4);
            } else {
                throw new Exception("Error obteniendo Imagenes");
            }
            String[] strip = imgStrip.split("%");
            for (String s : strip) {
                imagenes = imagenes + "|" + imgBase + s;
            }
            c.setExtra(imagenes);
        }
        String[] imagenes = c.getExtra().split("\\|");
        return imagenes[page];
    }

    private void getExtraWeb(Chapter c) throws Exception {
        String source = getBrowserWhitHeaders().get(c.getPath(), TIMEOUT);
        String fs = getFirstMacth("(http://www.tumangaonline.com/visor/.+?)\"", source, "Error al iniciar Capítulo");
        c.setExtra(fs);
    }

    @Override
    public void chapterInit(Chapter c) throws Exception {
        if (!(c.getExtra() != null && c.getExtra().length() > 1)) {
            getExtraWeb(c);
        }
        String source = getBrowserWhitHeaders().get(c.getExtra());
        String paginas = getFirstMacth("<input id=\"totalPaginas\" hidden=\"true\" value=\"(\\d+)\">", source, "Error al iniciar Capítulo");
        c.setPages(Integer.parseInt(paginas));
    }

    @Override
    public ArrayList<Manga> getMangasFiltered(int categorie, int order, int pageNumber) throws Exception {
        String source = getBrowserWhitHeaders().get(generosV[categorie] + "&pag=" + pageNumber, TIMEOUT);
        return getMangasFromSource(source);
    }

    private ArrayList<Manga> getMangasFromSource(String source) {
        Pattern p = Pattern.compile("2\">[\\s]*<a href=\"([^\"]+)\"[^<]+<img src=\"([^\"]+)\".+?alt=\"([^\"]+)\"");
        Matcher m = p.matcher(source);
        ArrayList<Manga> mangas = new ArrayList<>();
        while (m.find()) {
            Manga manga = new Manga(TUSMANGAS, m.group(3), m.group(1), false);
            manga.setImages(m.group(2));
            mangas.add(manga);
        }
        return mangas;
    }

    @Override
    public String[] getCategories() {
        return generos;
    }

    @Override
    public String[] getOrders() {
        return new String[]{"Alfabetico"};
    }

    @Override
    public boolean hasList() {
        return false;
    }

}
