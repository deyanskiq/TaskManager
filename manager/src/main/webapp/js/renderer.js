"use strict";

var Renderer;
var template_ext;
var template_dir;
Renderer = (function ($, Handlebars) {
        template_ext = '.handlebars';
		template_dir = 'templates/';
    return {
        render: function render(template_name, data) {
					$.ajax({
						url: template_dir + template_name + template_ext,
						method: 'GET',
						async: false,
						success: function (template) {
							template_name = Handlebars.compile(template);
                    }
                });
            

            return template_name(data);
        }
    };
})(jQuery, Handlebars);