define(function(require,exports,module){
	var hash = window.location.hash.slice(1);
    $('.J_nav').on('click', '.nav', function(e){
        var $this = $(this),
            name = $this.attr('data-name');
        $('.J_box').css('display', 'none')
            .filter('.' + name).css('display', 'block');

    });
    $('.J_nav').find('[data-name="' + hash + '"]').trigger('click');

});