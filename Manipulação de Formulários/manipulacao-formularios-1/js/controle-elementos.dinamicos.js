(function($){
    $.fn.dinamicElement = function(settings){
        /**
         * CONFIGURAÇÕES
         * @type type
         */
        var config = {
            container: ".dinamicContainer",
            dinamicContent: ".dinamicContent",
            btnAdd: ".btnAdd",
            imageBtnAdd: "images/inserir.gif",
            btnFechar: ".btnFechar",
            imageBtnFechar: "images/excluir.gif",
            editMode: false,
            animate: false,
            addAnimateFunction: function(item) {
                $(item).toggle("slow");
            },
            removeAnimateFunction: function(item) {
                $(item).toggle("slow", function() {
                    item.remove();
                })
            },
            afterAdd: null,
            afterRemove: null,
        };
        
        if (typeof settings == 'undefined') {
            settings = {};
        }
        
        config.dinamicContent = this.selector;
        
        if (settings){ $.extend(config, settings); }
 
        var addElement = function() {
            /** CRIA UM NOVO ÍTEM. */
            var itemAdicionar = $(config.dinamicContentRaw).first().clone().hide();
            itemAdicionar.appendTo(config.container);
            
            /** ADICIONA O BOTÃO DE REMOVER */
            var btnFechar = config.btnFechar.clone();
            btnFechar.appendTo(itemAdicionar);
            $("<div>").css("clear", "both").appendTo(itemAdicionar);
            
            
            /** ANIMA O ÍTEM */
            if(config.animate) {
                config.addAnimateFunction(itemAdicionar);
            }
            else {
                itemAdicionar.show();
            }
            
            
            /**
             * ADICIONA UM LISTENER PARA O BOTÃO FECHAR.
             */
            btnFechar.click(function() {
                removeElement(this);
            });
            
            if(typeof(config.afterAdd) == 'function') {
                config.afterAdd(itemAdicionar);
            }
        };
        
        var removeElement = function(element) {
            var itemRemover = $(element).closest("li.dinamicItem");
            
            if(config.animate) {
                config.removeAnimateFunction(itemRemover);
            }
            else {
                itemRemover.remove();
            }
            
            if(typeof(config.afterRemove) == 'function') {
                config.afterRemove(itemRemover);
            }
        }
        
        
        /**
         * CONFIGURA OS ELEMENTOS
         */
        
        /** CONFIGURA O CONTAINER DE ELEMENTOS */
        config.container = $("<div>").attr("class", config.container).css("display", "inline-block");
        config.dinamicContent = $(config.dinamicContent);
        
        var li = $("<li>").attr("class", "dinamicItem").css("list-style-type", "none");
        
        if(config.editMode == true) {
            config.dinamicContent.wrap(li);
            config.dinamicContent = $(config.dinamicContent).parent();
            config.dinamicContent.wrapAll($("<ul>"));
            config.dinamicContent.parent().wrap(config.container);
            config.container = $(config.dinamicContent.parent()).first();            
        }
        else {
            li.hide();
            config.dinamicContent.wrap(li);
            config.dinamicContent = config.dinamicContent.parent();
            config.dinamicContent.wrap($("<ul>"));
            config.dinamicContent.parent().wrap(config.container);
            config.container = $(config.dinamicContent.parent()).first();
        }
        
        /**
         * CLONE DO ELEMENTO DINAMICO (SEM BOTÃO FECHAR CONFIGURADO) PARA SER CLONADO QUANDO FOR ADICIONAR ELEMENTOS.
         */
        config.dinamicContentRaw = config.dinamicContent.clone();

        
        /** CONFIGURA O BOTÃO ADICIONAR */
        if(settings.btnAdd) {
            /** SE O USUÁRIO ESPECIFICOU O BOTÃO ADICIONAR, APENAS ADICIONA-SE UM LISTENER A ELE. */
            config.btnAdd = $(config.btnAdd)
                    .click(function() {
                        addElement();
                    }).show();
        }
        else {
            /** CASO CONTRÁRIO CRIA-SE O BOTÃO E ADICIONA-SE UM LISTENER A ELE. */
            config.btnAdd = $("<div>")
                    .attr("class", config.btnAdd)
                    .css("list-style-type", "none")
                    .css("text-align", "center")
                    .html(" Adicionar ")
                    .click(function() {
                        addElement();
                    })
                    .appendTo(config.container.parent());
            
            /** CRIA-SE UMA IMAGEM REPRESENTATIVA PARA O BOTÃO. */
            config.imageBtnAdd = $("<img>").attr("src", config.imageBtnAdd);
            config.imageBtnAdd.prependTo(config.btnAdd);
        }
        
        
        /**
         * CONFIGURA O BOTÃO FECHAR
         */
        if(settings.btnFechar) {
            config.btnFechar = $(config.btnFechar);
        }
        else {
            config.btnFechar = $("<div>").attr("class", config.btnFechar).css("display", "table-cell").html($("<img>").attr("src", config.imageBtnFechar).css("vertical-align", "middle"));
        }
        
        /**
         * CASO JÁ HAJA ELEMENTOS DINAMICOS NA TELA, INSERE O BOTÃO FECHAR NELES.
         */
        $(config.btnFechar.clone()).click(function() {
            removeElement(this);
        }).appendTo(config.dinamicContent);
        
        $("<div>").css("clear", "both").appendTo(config.dinamicContent);
                
 
        return this;
    };
})(jQuery);