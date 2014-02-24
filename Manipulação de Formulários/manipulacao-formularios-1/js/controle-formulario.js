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
        };
        
        if (typeof settings == 'undefined') {
            settings = {};
        }
        
        config.dinamicContent = this.selector;
        
        if (settings){ $.extend(config, settings); }
 
        var addElement = function() {
            /** CRIA UM NOVO ÍTEM. */
            var item = config.dinamicContent.clone();
            item.appendTo(config.container);
            
            /** ADICIONA O BOTÃO DE REMOVER */
            var btnFechar = config.btnFechar.clone();
            btnFechar.appendTo(item);
            
            
            /** ANIMA O ÍTEM */
            item.toggle("slow");
            
            
            /**
             * ADICIONA UM LISTENER PARA O BOTÃO FECHAR.
             */
            btnFechar.click(function() {
                removeElement(this);
            });
        };
        
        var removeElement = function(element) {
            $(element).closest("li.dinamicItem").toggle("slow", function() {
                $(element).closest("li.dinamicItem").remove();
            });
        }
        
        
        /**
         * CONFIGURA OS ELEMENTOS
         */
        
        /** CONFIGURA O CONTAINER DE ELEMENTOS */
        config.container = $("<div>").attr("class", config.container);
        config.dinamicContent = $(config.dinamicContent);
        
        
        config.dinamicContent.wrap($("<li>").attr("class", "dinamicItem").hide());
        config.dinamicContent = config.dinamicContent.parent();
        config.dinamicContent.wrap("<ul>");
        config.dinamicContent.parent().wrap(config.container);
        config.container = config.dinamicContent.parent().parent();
        

        
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
            config.btnAdd = $("<li>")
                    .attr("class", config.btnAdd)
                    .html(" Adicionar ")
                    .click(function() {
                        addElement();
                    })
                    .appendTo(config.container);
            
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
            config.btnFechar = $("<div>").attr("class", config.btnFechar).html('<img src="'+ config.imageBtnFechar +'" />');
        }
                
 
        return this;
    };
})(jQuery);