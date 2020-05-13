$(document).ready(function () {
    // 搜索
    $(".nav-search").click(function () {
        $("#search-main").fadeToggle(300);
    });

    // 菜单
    $(".nav-mobile").click(function () {
        $("#mobile-nav").slideToggle(500);
    });


    // 分享 √
    if (/iphone|ipod|ipad|ipad|mobile/i.test(navigator.userAgent.toLowerCase())) {
        $('.share-sd').click(function () {
            $('#share').animate({
                    opacity: 'toggle',
                    top: '-80px'
                },
                500).animate({
                    top: '-60px'
                },
                'fast');
            return false;
        });
    } else {
        $(".share-sd").mouseover(function () {
            $(this).children("#share").show();
        });
        $(".share-sd").mouseout(function () {
            $(this).children("#share").hide();
        });
    }


    // 去边线
    $(".message-widget li:last, .message-page li:last, .hot_commend li:last, .search-page li:last, .my-comment li:last, .message-tab li:last").css("border", "none");


    // 字号 √
    $("#fontsize").click(function () {
        var _this = $(this);
        var _t = $(".single-content");
        var _c = _this.attr("class");
        if (_c == "size_s") {
            _this.removeClass("size_s").addClass("size_l");
            _this.text("A+");
            _t.removeClass("fontsmall").addClass("fontlarge");
        } else {
            _this.removeClass("size_l").addClass("size_s");
            _this.text("A-");
            _t.removeClass("fontlarge").addClass("fontsmall");
        }
        ;
    });


// 结束
});


// 隐藏侧边
function pr() {
    var R = document.getElementById("sidebar");
    var L = document.getElementById("primary");
    if (R.className == "sidebar") {
        R.className = "sidebar-hide";
        L.className = "";
    } else {
        R.className = "sidebar";
        L.className = "primary";
    }
}



// 文字滚动
(function ($) {
    $.fn.textSlider = function (settings) {
        settings = jQuery.extend({
                speed: "normal",
                line: 2,
                timer: 1000
            },
            settings);
        return this.each(function () {
            $.fn.textSlider.scllor($(this), settings)
        })
    };
    $.fn.textSlider.scllor = function ($this, settings) {
        var ul = $("ul:eq(0)", $this);
        var timerID;
        var li = ul.children();
        var _btnUp = $(".up:eq(0)", $this);
        var _btnDown = $(".down:eq(0)", $this);
        var liHight = $(li[0]).height();
        var upHeight = 0 - settings.line * liHight;
        var scrollUp = function () {
            _btnUp.unbind("click", scrollUp);
            ul.animate({
                    marginTop: upHeight
                },
                settings.speed,
                function () {
                    for (i = 0; i < settings.line; i++) {
                        ul.find("li:first").appendTo(ul)
                    }
                    ul.css({
                        marginTop: 0
                    });
                    _btnUp.bind("click", scrollUp)
                })
        };
        var scrollDown = function () {
            _btnDown.unbind("click", scrollDown);
            ul.css({
                marginTop: upHeight
            });
            for (i = 0; i < settings.line; i++) {
                ul.find("li:last").prependTo(ul)
            }
            ul.animate({
                    marginTop: 0
                },
                settings.speed,
                function () {
                    _btnDown.bind("click", scrollDown)
                })
        };
        var autoPlay = function () {
            timerID = window.setInterval(scrollUp, settings.timer)
        };
        var autoStop = function () {
            window.clearInterval(timerID)
        };
        ul.hover(autoStop, autoPlay).mouseout();
        _btnUp.css("cursor", "pointer").click(scrollUp);
        _btnUp.hover(autoStop, autoPlay);
        _btnDown.css("cursor", "pointer").click(scrollDown);
        _btnDown.hover(autoStop, autoPlay)
    }
})(jQuery);

$("#scrolldiv").textSlider({line: 1, speed: 300, timer: 6000});


function confirmDelete() {
    var msg = "您确定要删除吗？";
    if (confirm(msg) == true) {
        return true;
    } else {
        return false;
    }
}

//退出登录
function logout() {
    $.ajax({
        async: false,
        type: "POST",
        url: '/admin/logout',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "text",
        complete: function () {
            window.location.reload();
        }
    })
}

//退出登录
function userLogout() {
    $.ajax({
        async: false,
        type: "POST",
        url: '/user/logout',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "text",
        complete: function () {
            window.location.reload();
        }
    })
}

//删除评论
function deleteComment(id) {
    if (confirmDelete() == true) {
        $.ajax({
            async: false,
            type: "POST",
            url: '/admin/comment/delete/' + id,
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            dataType: "text",
            complete: function () {
                window.location.reload();
            }
        })
    }
}

//评论区域
$(".comment-reply-link").click(function () {
    var authorName = $(this).parents('.comment-author').find("strong").text();
    $("#cancel-comment-reply-link").show();
    $("#reply-title-word").html("回复 " + authorName);
    var commentId = $(this).parents('.comment-body').attr("id").match(/\d+/g);
    $("input[name=commentPid]").attr("value", commentId);
    $("input[name=commentPname]").attr("value", authorName);
    $("#comment").attr("placeholder", "@ " + authorName)
})

$("#cancel-comment-reply-link").click(function () {
    $("#cancel-comment-reply-link").hide();
    $("input[name=commentPid]").attr("value", 0);
    $("input[name=commentPname]").attr("value", "");
    $("#reply-title-word").html("发表评论");
})

//文章浏览量+1
function increaseViewCount(articleId) {
    if ($.cookie("viewId") != articleId || $.cookie("viewId") == null) {
        $.ajax({
            async: false,
            type: "POST",
            url: "/article/view/" + articleId,
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                console.log(data);
                $(".articleViewCount").html(data);
                $.cookie(
                    "viewId",
                    articleId,//需要cookie写入的业务
                    {
                        "path": "/", //cookie的默认属性
                    }
                );
            },
            error: function () {
                alert("获取数据出错!");
            },
        });
    }
}


//点赞+1
function increaseLikeCount() {
    if ($.cookie("likeId") != articleId || $.cookie("likeId") == null) {
        $.ajax({
            async: false,
            type: "POST",
            url: "/article/like/" + articleId,
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                $(".count").html(data);
                $.cookie(
                    "likeId",
                    articleId,//需要cookie写入的业务
                    {
                        "path": "/", //cookie的默认属性
                    }
                );
            },
            error: function () {
                //alert("获取数据出错!");
            },
        });
    }
}


//ajax提交评论信息
$("#comment_form").submit(function () {
    $.ajax({
        async: false,
        type: "POST",
        url: '/comment',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: $("#comment_form").serialize(),
        success: function (data) {
            if (data.code == 0) {
                layer.msg("评论成功！");
                window.setTimeout("window.location.reload()", 2000);
                return false;
            } else {
                layer.msg(data.msg);
            }

        },
        error: function () {
        }
    })
    return false;
})


//申请友情链接
$("#applyLinkForm").submit(function () {
    $.ajax({
        async: false,
        type: "POST",
        url: '/applyLinkSubmit',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: $("#applyLinkForm").serialize(),
        success: function () {
            alert("申请成功，请耐心等待审核！");
        }
    })
})

//隐藏和显示阅读全文按钮
$('.post').mouseover(function () {
    $(this).find('.title-l').show();
    $(this).find('.entry-more').show();
});
$('.post').mouseleave(function () {
    $(this).find('.title-l').hide();
    $(this).find('.entry-more').hide();
});