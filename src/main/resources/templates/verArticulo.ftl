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
            <div class="col-md-12">
                <div class="col-md-12" style="background-color: burlywood">
                    <h3 class="article-title" id="article-title">
                    ${articulo.getTitulo()}
                    </h3>
                </div>

                <p class="article-preview" id ="article-preview">
                ${articulo.getCuerpo()}
                </p>
            </div>
        </div>
        <div class="row">

            <div class="col-md-3" id="by"><strong>By:</strong><span>${articulo.getAutor().getNombre()}</span></div>

            <div class="col-md-7">
            <#assign articuloEtiqueta = articulo.getEtiquetas()>
            <#if articuloEtiqueta?size != 0>
                <div class="col-md-offset-1 col-md-6 article-tags">
                    <div style="display: flex">
                      <b>Tags:</b>
                        <#list articuloEtiqueta as etiqueta>
                                <a class="btn icon-btn btn-default" style="color: #ffffff;font-size:14px;margin:4px;background-color:#9d9d9d;"  href="/etiqueta/${etiqueta.getId()}/articulos">
                                    <span class="glyphicon glyphicon-tags"></span>${etiqueta.getEtiqueta()}
                                </a>
                        </#list>
                    </div>

                </div>
            </#if>
            </div>

        </div>

        <div class="row">
            <div class="col-md-offset-8 col-md-4">
                <div id="date"><strong>Date:</strong><span style="display:inline-block">${articulo.getFecha()}</span></div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <#assign comentarios = articulo.getComentarios()>
                <#if comentarios?size!=0>
                <#list comentarios as comentario>
                    <p id="comentario" >${comentario.getComentario()}</p>


                        <a class="icono-boton" href="/likes/${comentario.getId()}/${articulo.getId()}">
                            <span class="glyphicon btn-glyphicon glyphicon-thumbs-up img-circle text-primary icono">${comment_likes}</span>
                        </a>

                        <a class="icono-boton" href="/dislikes/${comentario.getId()}">
                            <span class="glyphicon btn-glyphicon glyphicon-thumbs-down img-circle text-primary icono">${comment_dislikes}</span>
                        </a>


                </#list>

            </#if>
        </div>
        </div>

        <form method="post" action="/agregar/comentario/${articulo.getId()}">
            <div class="row">
                <div class="col-md-11">
                    <div class="form-group">
                        <textarea type="text" class="form-control" name="comentario" placeholder="Agrega tu comentario"
                                  id="comment" aria-describedby="sizing-addon1" rows="1" required></textarea>
                    <</div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-offset-2 col-md-5">

                    <button class="btn btn-lg btn-primary btn-rounded form-control" type="submit"
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
        $('.icono').fadeTo('slow',0.25);
        $(".icono").mouseleave(function () {
            $(this).fadeTo('slow',0.25);

        }).mouseenter(function () {
            $(this).fadeTo('slow',1);
        });

    });


</script>
<#include "footer.ftl">