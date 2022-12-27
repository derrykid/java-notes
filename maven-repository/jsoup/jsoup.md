## Connect and get doc

Document is the html file of the page
```java
Document indexDoc = Jsoup.connect(beautyForumUri.toString()).cookie(COOKIE, "1").get();
```

## set cookie
```java
        Document indexDoc = Jsoup.connect(beautyForumUri.toString()).cookie(COOKIE, "1").get();
```

## Elements

Elements is a list of Element. Elements can be get by html class, id, etc.
```java
Elements divElements = indexDoc.getElementsByClass("className");
```

You can see what a element is like in next section. Elements is just a collection of it.

## Element

This is an element, the elements is just 1... many of it.

```html
		<div class="r-ent">
			<div class="nrec"><span class="hl f3">10</span></div>
			<div class="title">
			
				<a href="/bbs/Beauty/M.1657718154.A.D65.html">[正妹] 馬尾 韓國女偶像篇</a>
			
			</div>
			<div class="meta">
				<div class="author">HarunaOno</div>
				<div class="article-menu">
					
					<div class="trigger">&#x22ef;</div>
					<div class="dropdown">
						<div class="item"><a href="/bbs/Beauty/search?q=thread%3A%5B%E6%AD%A3%E5%A6%B9%5D&#43;%E9%A6%AC%E5%B0%BE&#43;%E9%9F%93%E5%9C%8B%E5%A5%B3%E5%81%B6%E5%83%8F%E7%AF%87">搜尋同標題文章</a></div>
						
						<div class="item"><a href="/bbs/Beauty/search?q=author%3AHarunaOno">搜尋看板內 HarunaOno 的文章</a></div>
						
					</div>
					
				</div>
				<div class="date"> 7/13</div>
				<div class="mark"></div>
			</div>
		</div>
```

With an understanding of that, use Stream of forEach loop to get the thing
```java
elements.stream()
    .map(e -> e.select("span").text())
    .toList();
```
This code gives a list of text in `span` tag

## get the html string
```java
Element element;
String html = element.html();
```

## get the div inside div

```java
Element element;
element.select("div.anotherDivClassName").text();
```

## get the attribute of the tag
```java
element.attr(///);
```

## get the absolute class name
```java
element.select("a").attr("abs:href");
```
