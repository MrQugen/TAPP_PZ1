

//scrollTo start

;(function (){

    var body = document.querySelector('body');

    var closestAttr = function(item, attr){
        var node = item;

        while(node){
            var attrValue = node.getAttribute(attr);
            if(attrValue){
                return attrValue;
            }

            node = node.parentElement;
        }

        return null;
    };

    var scroll = function(target){
        var targetTop = target.getBoundingClientRect().top;
        var scrollTop = window.pageYOffset;
        var targetOffsetTop = targetTop + scrollTop;
        var headerOffset = document.querySelector('.header-page').clientHeight;

        window.scrollTo(0, targetOffsetTop - headerOffset);
    }

    body.addEventListener('click', function(e){
        var target = e.target;
        var scrollToItemClass = closestAttr(target, 'data-scroll-to');

        if(scrollToItemClass == null)
        {
            return;
        }

        e.preventDefault();
        var scrollToItem = document.querySelector('.' + scrollToItemClass);

        if(scrollToItem){
            scroll(scrollToItem)
        }

    });

})();

//scrollTo end

//catalog start

;(function(){

    closestItemByClass = function(item, className){
        var node = item;

        while(node){
            if(node.classList.contains(className)){
                return node;
            }

            node = node.parentElement;
        }

        return null;
    };

    var catalogSection = document.querySelector('.section-catalog');

    if(catalogSection === null){
        return;
    }

    var removeChildren = function(item){
        while(item.firstChild){
            item.removeChild(item.firstChild);
        }
    }

    var updateChildren = function(item, children){
        removeChildren(item);
        console.log(children);
        for(var i = 0; i < children.length; i += 1){
            item.appendChild(children[i]);
        }
    };

    var catalog = catalogSection.querySelector('.catalog');
    var catalogNav = catalogSection.querySelector('.catalog-nav');
    var catalogItems = catalogSection.querySelectorAll('.catalog_item');

    catalogNav.addEventListener('click', function(e){
        var target = e.target;
        var item = closestItemByClass(target, 'catalog-nav_btn');

        if(item === null || item.classList.contains('is-active'))
        {
            return;
        }

        e.preventDefault();

        var filterValue = item.getAttribute('data-filter');
        var previousBtnActive = catalogNav.querySelector('.catalog-nav_btn.is-active');

        previousBtnActive.classList.remove('is-active');
        item.classList.add('is-active');

        if(filterValue === 'all'){
            updateChildren(catalog, catalogItems);
            return;
        }

        var filteredItems = [];
        for(var i = 0; i < catalogItems.length; i += 1){
            var current = catalogItems[i];
            if(current.getAttribute('data-category') === filterValue){
                filteredItems.push(current);
            }
        }

        updateChildren(catalog, filteredItems);
    });
})();

//catalog end

document.addEventListener('click', function (e) {
    if (e.target.classList.contains("increase")) {
        ++e.target.parentElement.querySelector("input").value;
    } else if (e.target.classList.contains("decrease")) {
        if(e.target.parentElement.querySelector("input").value <= 1){ }
        else {
            --e.target.parentElement.querySelector("input").value;
        }
    }
})