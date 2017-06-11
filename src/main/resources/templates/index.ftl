<#include "header.ftl">
<#include "nav.ftl">

<!-- Used to list article sin index site -->

<#if articulos??>
    <#if articulos?size!=0>
        <#list articulos as articulo>
            <div class="article-container">

                    <div class="row">
                    <div class="col-md-12">
                          <div class="col-md-12" style="background-color: burlywood">
                              <a href="/ver/articulo/${articulo.getId()}" style="text-align: center">
                                  <h3 class="article-title">${articulo.getTitulo()}</h3>
                              </a>
                          </div>

                        <#assign cuerpoArticulo = articulo.getCuerpo()>
                        <#if cuerpoArticulo?length &gt; 70>
                            <#assign maxLength = 70>
                        <#else>
                            <#assign maxLength = cuerpoArticulo?length>
                         </#if>
                        <p class="article-preview">${cuerpoArticulo?substring(0, maxLength)} ...<a style="color:blue; font-weight: bold" href="/ver/articulo/${articulo.getId()}" >Leer mas</a></p>

                    </div>

                    <div class="row">
                        <div class="col-md-offset-8 col-md-4">
                            <p><b style="">By:</b> <a href="/usuario/${articulo.getAutor().getId()}">${articulo.getAutor().getNombre()} <i class="fa fa-user"></i></a></p>
                        </div>

                        <#assign articuloEtiqueta = articulo.getEtiquetas()>
                        <#if articuloEtiqueta?size != 0>
                            <div class="col-md-offset-1 col-md-6 article-tags">
                                <p>
                                <div style="display: flex">
                                    Etiqueta(s) <i class="fa fa-tags"></i>:

                                    <#list articuloEtiqueta as etiqueta>
                                        <div style="background-color: #5bc0de; margin:6px">
                                            <span style="font-size:15px;color:white">${etiqueta.getEtiqueta()}</span>
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
                    <div class="col-md-12" style="color:darkgray">
                        <h3>${noDatos}...<a href="/agregar/articulo" style="text-align: center">Agregar un articlo?
                        </a></h3>
                    </div>
                </div>
            </div>
         </#if>
    </#if>
</#if>

<#include "footer.ftl">


