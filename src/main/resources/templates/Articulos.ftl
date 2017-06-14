<#include "header.ftl">
<#include "nav.ftl">

<!-- Used to list article sin index site -->

<#if articulos??>
    <#if articulos?size!=0>
        <#list articulos as articulo>
        <div class="article-container">
            <div class="row">
                <div class="col-md-offset- 2 col-md-9" id="header">
                    <a id="" href="/ver/articulo/${articulo.getId()}">
                        <h3 class="article-title">${articulo.getTitulo()}</h3>
                    </a>
                </div>

            </div>
            <div class="row">
                <div class="col-md-offset-2 col-md-9">
                    <#assign cuerpoArticulo = articulo.getCuerpo()>
                    <#if cuerpoArticulo?length &gt; 70>
                        <#assign maxLength = 70>
                    <#else>
                        <#assign maxLength = cuerpoArticulo?length>
                    </#if>
                    <p class="article-preview">${cuerpoArticulo?substring(0, maxLength)} ...<a href="/ver/articulo/${articulo.getId()}" >Leer mas</a></p>

                    <#assign result = articulos?size/5>

                    <p id="cant" style="display: none">${result?ceiling}</p>
                </div>

            </div>

            <div class="row">
                <div class="col-md-offset-8 col-md-4">
                    <p><b>By:</b> <a href="/usuario/${articulo.getAutor().getId()}">${articulo.getAutor().getNombre()} <i class="fa fa-user"></i></a></p>
                </div>

                <#assign articuloEtiqueta = articulo.getEtiquetas()>
                <#if articuloEtiqueta?size != 0>
                    <div class="col-md-offset-1 col-md-6 article-tags">
                        <p>
                        <div >
                            Etiqueta(s) <i class="fa fa-tags"></i>:

                            <#list articuloEtiqueta as etiqueta>
                                <div class="tags">
                                    <a style="color: #ffffff;font-size:14px;margin:4px;padding:5px 5px 5px 5px;background-color:#9d9d9d;" class="glyphicon glyphicon-tags" href="/etiqueta/${etiqueta.getId()}/articulos">${etiqueta.getEtiqueta()}</a>
                                </div>

                            </#list>
                        </div>

                        </p>
                    </div>
                </#if>

            </div>

        </div>

        </div>
        <hr>
        </#list>
    <#else>
        <#if noDatos??>
        <div class="row">
            <div class="col-md-12">
                <div class="col-md-12">
                    <h3>${noDatos}...<a href="/agregar/articulo">Agregar un articlo?
                    </a></h3>
                </div>
            </div>
        </div>
        </#if>
    </#if>
</#if>
<div class="row">
    <div class="col-md-offset-5">
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">Previous</span>
                    </a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                        <span class="sr-only">Next</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</div>
<div class="demo2">Just for test</div>
<div class="content2">page here</div>
<script type="text/javascript">
    var cant = parseInt($('#cant').text());
    alert(cant);
    $('.demo2').bootpag({
        total: cant,
        page: 1,
        maxVisible: 5,
        leaps:true
    }).on('page', function(event, num){
        loadPage(num)
    });
    function loadPage(num) {
        $.get("/");
    }
</script>
<#include "footer.ftl">


