<#include "header.ftl">
<#include "nav.ftl">
<div class="container">
    <div class="article-container">
        <div class="row">
            <div class="col-md-12">
                <div class="menu">
                    <h3>Options</h3>
                    <div>
                        <p >
                            <a class="links" href="/borrar/articulo/${articulo.getId()}">Borrar</a><br>
                            <a class="links" href="/agregar/articulo">Agregar</a><br>
                            <a class="links" href="/modificar/articulo/${articulo.getId()}">Modificar</a>
                        </p>

                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-offset-2 col-md-9">
                <div class="col-md-12" style="background-color: burlywood">
                    <h3 class="article-title">
                    ${articulo.getTitulo()}
                    </h3>
                </div>

                <p class="article-preview">
                ${articulo.getCuerpo()}
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-offset-2 col-md-9">
            <#assign articuloEtiqueta = articulo.getEtiquetas()>
            <#if articuloEtiqueta?size != 0>
                <div class="col-md-offset-1 col-md-6 article-tags">
                    <div style="display: flex">
                        Etiqueta(s) <i class="fa fa-tags"></i>:

                        <#list articuloEtiqueta as etiqueta>
                            <div style="background-color: #5bc0de; margin:6px">
                                <span style="font-size:15px;color:white">${etiqueta.getEtiqueta()}</span>
                            </div>

                        </#list>
                    </div>

                </div>
            </#if>
            </div>
        </div>


        <div class="row">
            <div class="col-md-offset-2 col-md-9">
                <div class="col-md-6">
                    <span style="float:left">${articulo.getAutor().getNombre()}</span>
                </div>
                <div class="col-md-6">
                    <span style="float:right">${articulo.getFecha()}</span>
                </div>


            </div>
        </div>
        <div class="row">
            <div class="col-md-offset-2 col-md-8">
            <#assign comentarios = articulo.getComentarios()>
            <#if comentarios?size!=0>
                <#list comentarios as comentario>
                    <div>
                        <p style="border:1px solid gray;background-color: white">${comentario.getComentario()}

                        </p>
                    </div>

                </#list>

            </#if>
            </div>
        </div>

        <form method="post" action="/agregar/comentario/${articulo.getId()}">
            <div class="row">
                <div class="col-md-offset-2 col-md-8">
                    <div class="form-group">
                        <textarea type="text" class="form-control" name="comentario" placeholder="Agrega tu comentario"
                                  id="comment" aria-describedby="sizing-addon1" rows="1"></textarea>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-offset-2 col-md-8">

                    <button class="btn btn-lg btn-primary btn-block btn-signin form-control" type="submit"
                            style="margin-bottom: 15px">Agregar
                    </button>
                </div>
            </div>
        </form>

    </div>

</div>
<script type="text/javascript">
    $(document).ready(function(){
        $(".menu").accordion({collapsible:true, active:false});
    });
</script>
<#include "footer.ftl">